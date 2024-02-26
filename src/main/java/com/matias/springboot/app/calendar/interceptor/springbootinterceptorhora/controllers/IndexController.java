package com.matias.springboot.app.calendar.interceptor.springbootinterceptorhora.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app")
public class IndexController {

    @Value("${config.hora.open}")
    private Integer hOpen;
    @Value("${config.hora.close}")
    private Integer hClose;
    Map<String, Object> data = new HashMap<>();

    
    @GetMapping("/foo")
    public ResponseEntity<?> foo(HttpServletRequest request){
        
        if(request.getAttribute("message") != null){
            data.put("title", "Bienvenido al sistema de atencion al cliente");
            data.put("time", new Date());
            data.put("message", request.getAttribute("message"));
        }
        // else{
        //     data.put("title", "Bienvenido al sistema de atencion al cliente");
        //     data.put("time", new Date());
        //     data.put("error", request.getAttribute("error"));
        // }
        
        return ResponseEntity.ok(data);

    }

    
}
