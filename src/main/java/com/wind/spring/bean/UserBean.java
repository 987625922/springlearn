package com.wind.spring.bean;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ToString
@Component
public class UserBean {
    private Person person;

    @Autowired
    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
