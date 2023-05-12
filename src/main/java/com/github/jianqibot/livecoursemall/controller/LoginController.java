package com.github.jianqibot.livecoursemall.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class LoginController {

    private final Map<String,String> userLoginInfo = new ConcurrentHashMap<>();
    private final Map<String,String> userSessionInfo = new ConcurrentHashMap<>();

    {
        userLoginInfo.put("David", "abc123");
        userLoginInfo.put("Bob","zxc456");
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(String username, String password, HttpServletResponse response) {

        if (password.equals(userLoginInfo.get(username))) {
            // generate session info
            String sessionId = UUID.randomUUID().toString();
            String cookieName = "Cookie-Name-" + UUID.randomUUID().toString().substring(0,7);
            userSessionInfo.put(cookieName,sessionId);
            // add cookie to response
            response.addCookie(new Cookie(cookieName, sessionId));

            return "User has login successfully";
        } else {
            return "Login failed";
        }
    }
}
