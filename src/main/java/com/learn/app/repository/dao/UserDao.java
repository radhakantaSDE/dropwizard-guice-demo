package com.learn.app.repository.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface UserDao {

    @SqlQuery("select name from student where id = :id")
    String findNameById(@Bind("id") int id);
}
