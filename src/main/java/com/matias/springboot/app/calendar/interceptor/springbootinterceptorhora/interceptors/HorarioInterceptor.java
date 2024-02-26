package com.matias.springboot.app.calendar.interceptor.springbootinterceptorhora.interceptors;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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

        Calendar calendar = Calendar.getInstance();
        // instancia con la hora del dia
        Integer hora = calendar.get(calendar.HOUR_OF_DAY);

        if(hora >= hOpen && hora <= hClose){
            Map<String, String> json = new HashMap<>();
            json.put("mensaje", "Horiario de atención, desde las " + hOpen + " hasta las " + hClose);
            request.setAttribute("mensaje", json);
        }
        response.sendRedirect(request.getContextPath().concat("/close"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }
}
