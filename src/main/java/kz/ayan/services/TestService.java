package kz.ayan.services;

import kz.ayan.annotation.CheckRequest;
import kz.ayan.controllers.HomeController;
import kz.ayan.exceptions.CountException;
import kz.ayan.models.Deque;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayDeque;


@Service
public class TestService {
    private final static Logger log =  LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private Deque deque;


    public void test(){
        log.info("Call ok");
    }

    public boolean checker(String ip,int size,int time){
        log.info("IP: " + ip);
        Long now = Timestamp.valueOf(LocalDateTime.now()).getTime();
        ArrayDeque<Long> requests = deque.getDeque().get(ip);
        if(requests == null) {
            requests = new ArrayDeque<>();
            requests.addLast(now);
            deque.getDeque().put(ip,requests);
            return true;
        }
        else{
            if(requests.isEmpty()){
                requests.addFirst(now);
                return true;
            }else{
                if(compare(requests,size,time)){
                    log.info("Request pool size " + requests.size());
                    return true;
                }else{
                    log.info("ERROR");
                    throw new CountException();
                }
            }
        }
    }


    private boolean compare(ArrayDeque<Long> reqs,int size,int time){
        Long now = Timestamp.valueOf(LocalDateTime.now()).getTime();
        if(reqs.size() < size){
            reqs.addLast(now);
            return true;
        }else{
            resize(reqs, size);
            long diff = reqs.getFirst() - now;
            return diff >= (time * 60000);
        }
    }


    private void resize(ArrayDeque<Long> arrayDeque,int size) {
        while(arrayDeque.size() > size){
            arrayDeque.removeFirst();
        }
    }


}
