package com.example.siteautobarcode.controllers;


import com.example.siteautobarcode.DAO.DBConnection;
import com.example.siteautobarcode.GetBalance;
import com.example.siteautobarcode.Greeting;
import com.example.siteautobarcode.POJO.MePOJO;
import com.example.siteautobarcode.POJO.RowDB;
import com.example.siteautobarcode.POJO.RowKSO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class MainController {
    @RequestMapping(value="/greeting", method=RequestMethod.GET)
    public String greetingForm(Model model) {
        DBConnection dbConnection = new DBConnection();
        model.addAttribute("RowKSO", dbConnection.getAllDataForKSO());
        return "home";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@RequestParam(value = "token") String token, @RequestParam(value = "balance") float balance,
                                 Model model) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.setBalance(token, balance);
        model.addAttribute("RowKSO", dbConnection.getAllDataForKSO());
        return "home";
    }

    @RequestMapping(value = "/card", method = RequestMethod.GET)
    public String home(Model model, @RequestParam(value = "token") String token)
    {
        if(token != null) {
            GetBalance getBalance = new GetBalance();
            DBConnection dbConnection = new DBConnection();
            RowDB rowDB = dbConnection.getRow(token);
            model.addAttribute("cardNumber", "E" + rowDB.getCard());
            MePOJO mePOJO = getBalance.getBalance(rowDB.getToken());
            if(mePOJO != null)
                model.addAttribute("balance", mePOJO.getMainPointsBalance() / 100);
            else
                model.addAttribute("balance", "-");
            return "home1";
        } else
            return "error";
    }

    @RequestMapping(value = "/kso", method = RequestMethod.GET)
    public String kso(Model model, @RequestParam(value = "token") String token)
    {
        if(token != null) {
            DBConnection dbConnection = new DBConnection();
            RowKSO rowKSO = dbConnection.getRowForKSO(token);
            model.addAttribute("cardNumber", "E" + rowKSO.getCard());
                model.addAttribute("balance", "Err");
            return "test";
        } else
            return "error";
    }

    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public String getTransaction(Model model, @RequestParam(value = "token") String token)
    {
        GetBalance getBalance = new GetBalance();
        DBConnection dbConnection = new DBConnection();
        RowDB rowDB = dbConnection.getRow(token);
        String str = getBalance.getTransaction(rowDB.getToken());
        if(str != null) {
            model.addAttribute("transaction", str);
            return "transaction";
        }
        else
            return "error";
    }
}
