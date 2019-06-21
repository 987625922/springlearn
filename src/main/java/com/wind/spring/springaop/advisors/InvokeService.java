package com.wind.spring.springaop.advisors;

import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class InvokeService {
    public void invoke() {
        System.out.println("invokeService...");
    }

    public void invokeException() {
        throw new PessimisticLockingFailureException("");
    }
}
