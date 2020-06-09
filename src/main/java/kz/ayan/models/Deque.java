package kz.ayan.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.concurrent.ConcurrentHashMap;

@Scope(value = "singleton")
@Component
public class Deque {
    ConcurrentHashMap<String, ArrayDeque<Long>> hashMap;

    public Deque(){ this.hashMap = new ConcurrentHashMap<>(); }
    public ConcurrentHashMap<String,ArrayDeque<Long>> getDeque(){
        return this.hashMap;
    }
}
