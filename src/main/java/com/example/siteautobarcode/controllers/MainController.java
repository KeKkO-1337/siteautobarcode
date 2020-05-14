package com.example.siteautobarcode.controllers;


import com.example.siteautobarcode.DAO.DBConnection;
import com.example.siteautobarcode.GetBalance;
import com.example.siteautobarcode.POJO.MePOJO;
import com.example.siteautobarcode.POJO.RowDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model)
    {
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
