package com.example.siteautobarcode.controllers;


import com.example.siteautobarcode.DAO.DBConnection;
import com.example.siteautobarcode.GetBalance;
import com.example.siteautobarcode.POJO.RowDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class MainController {
    GetBalance getBalance = new GetBalance();
    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    public String home(Model model, @PathVariable String token)
    {
        if(token != null) {
            DBConnection dbConnection = new DBConnection();
            RowDB rowDB = dbConnection.getRow(token);
            model.addAttribute("cardNumber", "E" + rowDB.getCard());
            model.addAttribute("balance", getBalance.getBalance(rowDB.getToken()).getMainPointsBalance() / 100);
            return "home";
        } else
            return "error";
    }
}
