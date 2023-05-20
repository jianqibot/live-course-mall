package com.github.jianqibot.livecoursemall.controller;

import com.github.jianqibot.livecoursemall.exceptions.model.HttpException;
import com.github.jianqibot.livecoursemall.interceptor.UserInterceptor;
import com.github.jianqibot.livecoursemall.model.Session;
import com.github.jianqibot.livecoursemall.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/session")
    public Session authStatus() throws HttpException {
        User currentUser = UserInterceptor.UserContext.getCurrentUser();
        if (currentUser == null) {
            throw new HttpException(401, "Unauthorized! Please login first");
        } else {
            Session session = new Session();
            session.setUser(currentUser);
            return session;
        }
    }

}
