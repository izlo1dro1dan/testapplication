package kz.ayan.controllers;

import kz.ayan.annotation.CheckRequest;
import kz.ayan.services.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api")
public class HomeController {

    private final static Logger log =  LoggerFactory.getLogger(HomeController.class);

    @Autowired
    TestService testService;
    @CheckRequest
    @RequestMapping(path = "/ok",method = RequestMethod.GET)
    public String ok(){
        String empty = "";
        testService.test();
        return empty;
    }
}
