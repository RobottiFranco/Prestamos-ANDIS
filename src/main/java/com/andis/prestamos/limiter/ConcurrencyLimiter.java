package com.andis.prestamos.limiter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.Semaphore;

@Component
public class ConcurrencyLimiter {

    private final Semaphore semaphore;

    // Utilizar el valor de application.properties
    public ConcurrencyLimiter(@Value("${app.max.concurrent.requests}") int maxConcurrentRequests) {
        this.semaphore = new Semaphore(maxConcurrentRequests);
    }

    public void acquire() {
        boolean acquired = semaphore.tryAcquire();
        if (!acquired) {
            System.out.println("no puede acceder");
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Too many concurrent requests");
        }
    }

    public void release() {
        semaphore.release();
    }
}