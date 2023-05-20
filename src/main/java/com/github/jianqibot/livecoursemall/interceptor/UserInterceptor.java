package com.github.jianqibot.livecoursemall.interceptor;

import com.github.jianqibot.livecoursemall.dao.SessionRepository;
import com.github.jianqibot.livecoursemall.model.Session;
import com.github.jianqibot.livecoursemall.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    SessionRepository sessionRepository;

    public static final String COOKIE_NAME = "MY_APP_SESSION_ID";

    public static class UserContext {
        private static ThreadLocal<User> currentUser = new ThreadLocal<>();

        // Get the user in current thread context, null means user has not logged in yet
        public static User getCurrentUser() {
            return currentUser.get();
        }

        // set the user for current context, null means clean up the current logged in user
        public static void setCurrentUser(User currentUser) {
            UserContext.currentUser.set(currentUser);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Fetch user according to cookie from database
        Cookie[] cookies = request.getCookies();
        Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(COOKIE_NAME))
                .map(Cookie::getValue).findFirst()
                .flatMap(sessionRepository::findByCookie)
                .map(Session::getUser)
                .ifPresent(UserContext::setCurrentUser);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        // Must clean threadlocal!!!
        UserContext.setCurrentUser(null);
    }
}
