package com.github.jianqibot.livecoursemall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jianqibot.livecoursemall.exceptions.model.HttpException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlingController {

    private ObjectMapper mapper = new ObjectMapper();

    @ExceptionHandler(HttpException.class)
    public void conflict(HttpServletResponse response, HttpException ex) throws IOException {

        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("message", ex.getErrorMessage());

        response.setStatus(ex.getHttpStatus());
        response.getOutputStream().write(mapper.writeValueAsBytes(jsonObject));
        response.getOutputStream().flush();
    }
}