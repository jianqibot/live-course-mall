package com.github.jianqibot.livecoursemall;

import com.github.jianqibot.livecoursemall.dao.UserRepository;
import com.github.jianqibot.livecoursemall.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@SpringBootApplication
public class LiveCourseMallApplication {


	public static void main(String[] args) {
		SpringApplication.run(LiveCourseMallApplication.class, args);
	}

}
