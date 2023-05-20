package com.github.jianqibot.livecoursemall.controller;



import com.github.jianqibot.livecoursemall.dao.UserRepository;
import com.github.jianqibot.livecoursemall.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@EnableJpaAuditing
public class LoginController {


    @Autowired
    private UserRepository userRepository;

    private final Map<String,String> userLoginInfo = new ConcurrentHashMap<>();
    private final Map<String,String> cookieToUsername = new ConcurrentHashMap<>();

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
            String cookieName = "Cookie-Name";
            cookieToUsername.put(cookieName,sessionId);
            // add cookie to response
            response.addCookie(new Cookie(cookieName, sessionId));

            return "User has login successfully";
        } else {
            return "Login failed";
        }
    }


    @GetMapping("/test")
    @ResponseBody
    public String testJpa() {
        Optional<User> user = userRepository.findById(1);
        if (user.isPresent()) {
            return user.get().getUsername();
        } else {
            return "result does not exist";
        }
    }

    @GetMapping("/test1")
    @ResponseBody
    public String testJpaS() {
        User user = new User();
        user.setUsername("aaa");
        user.setPassword("bbb");
        User savedUser = userRepository.save(user);
        return savedUser.getUsername();
    }
}
