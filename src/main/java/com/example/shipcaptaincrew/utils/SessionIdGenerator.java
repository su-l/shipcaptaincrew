package com.example.shipcaptaincrew.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class SessionIdGenerator {
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static Integer getUniqueId(){
        return counter.incrementAndGet();
    }
}
