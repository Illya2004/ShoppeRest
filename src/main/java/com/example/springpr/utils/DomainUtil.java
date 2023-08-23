package com.example.springpr.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class DomainUtil {
    public static String getCurrentDomain(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String domain = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        return domain;
    }
}
