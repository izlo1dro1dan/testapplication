package kz.ayan.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestServiceTest {

    @Autowired
    TestService testService;

    @Test
    void checker() {
        System.out.println("Start");
        assert testService.checker("33.33.33.33.33",5,5);
        assert testService.checker("33.33.33.33.33",5,5);
        assert testService.checker("33.33.33.33.33",5,5);
        assert testService.checker("33.33.33.33.33",5,5);
        assert testService.checker("33.33.33.33.33",5,5);
        assert testService.checker("33.33.33.33.33",5,5);
        assert testService.checker("33.33.33.33.34",5,5);
        assert testService.checker("33.33.33.33.35",5,5);
        assert testService.checker("33.33.33.33.36",5,5);
        assert testService.checker("33.33.33.33.37",5,5);
        System.out.println("Finish");
    }
}