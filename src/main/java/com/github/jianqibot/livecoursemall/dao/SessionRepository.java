package com.github.jianqibot.livecoursemall.dao;

import com.github.jianqibot.livecoursemall.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Integer> {

    Optional<Session> findByCookie(String cookie);
}
