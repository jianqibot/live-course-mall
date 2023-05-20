package com.github.jianqibot.livecoursemall.dao;

import com.github.jianqibot.livecoursemall.model.Status;
import com.github.jianqibot.livecoursemall.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    // select * from user where status != ok
    List<User> findByStatus(Status status);

    // if the SQL logic is vey complicated
    @Query(value="select * from user where id <> 2", nativeQuery = true)
    List<User> findByVeryComplicatedSql();
}
