package com.example.siteautobarcode.controllers;


import com.example.siteautobarcode.DAO.ChekedDAO;
import com.example.siteautobarcode.DAO.DBConnection;
import com.example.siteautobarcode.DAO.UsersDAO;
import com.example.siteautobarcode.GetBalance;
import com.example.siteautobarcode.POJO.RowDB;
import com.example.siteautobarcode.POJO.RowKSO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

@Controller
public class MainController {

    @RequestMapping(value = "/magnit", method = RequestMethod.GET)
    public String checkedCards(Model model, @RequestParam(value = "token") String token)
    {
        if(token != null) {
            ChekedDAO dbConnection = new ChekedDAO();
            RowKSO rowKSO = dbConnection.getRowForKSO(token);
            model.addAttribute("cardNumber", "E" + rowKSO.getCard());
            model.addAttribute("balance", (int)rowKSO.getBalance());
            return "test";
        } else
            return "error";
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String startPage() {
        System.out.println(getCurrentUsername());
        if(getCurrentUsername().equals("anonymousUser"))
            return "error";
        else
            return "redirect:/greeting";
    }

    @RequestMapping(value="/greeting", method=RequestMethod.GET)
    public String greetingForm(Model model) {
        DBConnection dbConnection = new DBConnection();
        String usr = getCurrentUsername();
        UsersDAO usersDAO = new UsersDAO();
        ArrayList<RowKSO> res = dbConnection.getAllDataForKSO(usr);
        Collections.sort(res);
        model.addAttribute("viewed", usersDAO.getUser(usr).getViewed());
        //model.addAttribute("earned", user.getEarned());
        model.addAttribute("RowKSO", res);
        return "home";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@RequestParam(value = "token") String token, @RequestParam(value = "balance") int balance,
                                 Model model) {
        String usrnam = getCurrentUsername();
        DBConnection dbConnection = new DBConnection();
        dbConnection.setBalance(token, balance, usrnam);
        UsersDAO usersDAO = new UsersDAO();
        String usr = getCurrentUsername();
        usersDAO.updateUserForCheck(usrnam, balance);
        ArrayList<RowKSO> res = dbConnection.getAllDataForKSO(usr);
        Collections.sort(res);
        model.addAttribute("viewed", usersDAO.getUser(usr).getViewed());
        model.addAttribute("RowKSO", res);
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
            //MePOJO mePOJO = getBalance.getBalance(rowDB.getToken());
                model.addAttribute("balance", rowDB.getBalance());
            return "home1";
        } else
            return "error";
    }

    @RequestMapping(value = "/kso", method = RequestMethod.GET)
    public String kso(Model model, @RequestParam(value = "token") String token)
    {
        if(token != null) {
            DBConnection dbConnection = new DBConnection();
            RowKSO rowKSO = dbConnection.getRowForKSO(token,getCurrentUsername());
            model.addAttribute("cardNumber", "E" + rowKSO.getCard());
            if(rowKSO.getBalance() == 0.0)
                model.addAttribute("balance", "Err");
            else
                model.addAttribute("balance", (int)rowKSO.getBalance());
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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testNewPage(Model model)
    {
        return "testNewPage.html";
    }

    @GetMapping("/registration")
    public String registreation()
    {
        return "registration";
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
