package org.learn.springdatajpa.dao;

import org.learn.springdatajpa.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends
        JpaRepository<JpaUser, Integer>, JpaSpecificationExecutor<JpaUser> {

}
