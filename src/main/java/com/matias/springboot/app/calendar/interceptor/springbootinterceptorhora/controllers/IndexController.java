package com.matias.springboot.app.calendar.interceptor.springbootinterceptorhora.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class IndexController {

    @Value("${config.hora.open}")
    private Integer hOpen;
    @Value("${config.hora.close}")
    private Integer hClose;

    @GetMapping("/foo")
    public ResponseEntity<?> foo(){

        Map<String, String> data = new HashMap<>();
        data.put("title", "Bienvenido al sistema de atencion al cliente");
        data.put("time", new Date().toString());
        return ResponseEntity.ok(data);
    }

    @GetMapping("/close")
    public ResponseEntity <?> close(){

        Map<String, String> data = new HashMap<>();
        data.put("error", "Fuera de horario de atencion, vualva a partir de las " + hOpen + " hasta " + hClose);
        data.put("time", new Date().toString());
        return ResponseEntity.ok(data);
    }
}
