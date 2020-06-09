package kz.ayan.annotation;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface CheckRequest {

    @Value("${limits.size}")
    int count = 1 ;

    @Value("${limits.time}")
    int time = 1;
}