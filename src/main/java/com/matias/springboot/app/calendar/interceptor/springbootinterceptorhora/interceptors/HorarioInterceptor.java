package com.matias.springboot.app.calendar.interceptor.springbootinterceptorhora.interceptors;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("horarioInterceptor")
public class HorarioInterceptor implements HandlerInterceptor{

    @Value("${config.hora.open}")
    private Integer hOpen;
    @Value("${config.hora.close}")
    private Integer hClose;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // obtenemos la hora actual
        Calendar calendar = Calendar.getInstance();
        Integer hora = calendar.get(calendar.HOUR_OF_DAY);

        Map<String, Object> messages = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        if(hora >= hOpen && hora <= hClose){
            messages.put("message", "Horiario de atención, desde las " + hOpen + " hasta las " + hClose);
            request.setAttribute("message", messages);
            return true;
        }
        // else{
        //     messages.put("error", "Fuera del horario de atencion al cliente, vuelva entre las " + hOpen + " y las " + hClose);
        //     request.setAttribute("error", messages);
        //     return true;
        // }

        // creamos un json leyendo el map
        messages.put("date", new Date().toString());
        messages.put("title", "Bienvenido al sistema de atención al cliente");
        messages.put("error", "Fuera del horario de atencion al cliente, vuelva entre las " + hOpen + " y las " + hClose);
        String jsonString = mapper.writeValueAsString(messages);
        response.setContentType("application/json"); 
        response.setStatus(401);
        response.getWriter().write(jsonString);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }
}
