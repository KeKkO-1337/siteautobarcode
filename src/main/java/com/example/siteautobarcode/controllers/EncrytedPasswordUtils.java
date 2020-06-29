package com.example.siteautobarcode.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {

        public static void main(String[] args) {

            int i = 0;
            while (i < 2) {
                String password = "2020";
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(password);

                System.out.println(hashedPassword);
                i++;
            }
        }
    }
