package kz.ayan.components;

import kz.ayan.annotation.CheckRequest;
import kz.ayan.exceptions.CountException;
import kz.ayan.models.Deque;
import kz.ayan.services.TestService;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayDeque;

@Component
public class CheckRequestHandlerInterceptorComponent extends HandlerInterceptorAdapter {
    private static final String[] HEADERS_TO_TRY = {"X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR" };

    @Value("${limits.size}")
    int count;

    @Value("${limits.time}")
    int time;
    private final static Logger log =  LoggerFactory.getLogger(CheckRequestHandlerInterceptorComponent.class);

    @Autowired
    TestService testService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            CheckRequest metho = method.getAnnotation(CheckRequest.class);
            CheckRequest clas = method.getDeclaringClass().getAnnotation(CheckRequest.class);

            if(metho != null)
                testService.checker(getIp(request),count,time);
            if(clas != null)
                testService.checker(getIp(request),count,time);

//            log.info("Check methods and class");
//            Reflections reflections = new Reflections(new ConfigurationBuilder()
//                    .setUrls(ClasspathHelper.forPackage("kz.ayan"))
//                    .setScanners(new MethodAnnotationsScanner(),new TypeAnnotationsScanner(),new SubTypesScanner()));
//
//            System.out.println("Start methods " + reflections.getMethodsAnnotatedWith(CheckRequest.class));
//
//            System.out.println("Start class " + reflections.getTypesAnnotatedWith(CheckRequest.class));

        }
        return true;
    }
    private String getIp(HttpServletRequest request){
        for(String header: HEADERS_TO_TRY){
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

}
