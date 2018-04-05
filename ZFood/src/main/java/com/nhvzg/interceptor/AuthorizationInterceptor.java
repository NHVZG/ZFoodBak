package com.nhvzg.interceptor;

import com.nhvzg.entity.User;
import com.nhvzg.tools.JsonTools;
import com.nhvzg.tools.TokenTools;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by NHVZG on 2018/3/6.
 */

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
       // String tokenStr = httpServletRequest.getParameter("token");
        /*Enumeration headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = httpServletRequest.getHeader(key);
            System.out.println(key+"@"+value);
        }*/
        String token = httpServletRequest.getHeader("token");
        String requestURI=httpServletRequest.getRequestURI();
        String str;
        if(!requestURI.contains("login")) {
            try {
                Cookie[] cookies = httpServletRequest.getCookies();
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("token")) {
                        token = cookie.getValue();
                        System.out.println(token);
                    }
                    System.out.println(cookie.getName()+"---"+cookie.getValue());
                }
                Map map=TokenTools.parseJWT(token);
                Map map1= (Map) map.get("user");
                httpServletRequest.setAttribute("userId",map1.get("userId"));
                httpServletRequest.setAttribute("userState",map1.get("state"));
                //System.out.println(map1.get("userId"));
                //throw new Exception();
            }catch (Exception e){
                if(e instanceof ExpiredJwtException)
                    str= "{\"errorCode\":408,\"message\":\"超时\",\"data\":null}";
                else
                    str="{\"errorCode\":403,\"message\":\"token无法验证\",\"data\":null}";
                ErrorReturn(httpServletRequest, httpServletResponse, str);
                return false;
            }
        }
        //httpServletResponse.setHeader("token", token);
        return true;
    }
    // 检测到没有token，直接错误信息返回不验证
    public void ErrorReturn(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Object obj){
        String json = (String)obj;
        PrintWriter writer = null;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html; charset=utf-8");
        System.out.println("ERROR---------------"+obj.toString());
        try {
            writer = httpServletResponse.getWriter();
            writer.print(json);
        } catch (IOException ex) {
            System.out.println("response error: "+ex);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
