package ttn.bootcamp.linksharing.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ttn.bootcamp.linksharing.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class Interceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("interceptor url : "+request.getRequestURI());
        HttpSession session = request.getSession();
//        if (request.getRequestURI().equals("/")||request.getRequestURI().equals("/login")) {
//            return true;
//        }
//        else{
//            if((User)session.getAttribute("user")==null){
//                System.out.println("session null");
//                return false;
//            }
//            return true;
//        }

        return true;
    }

}
