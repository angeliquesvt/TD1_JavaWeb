package com.esiee.tp2.filter;

import com.esiee.tp2.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = {"/*"}, description = "Authentification Filter")
public class AuthentificationFilter implements Filter {

    public static final String SUCCES_LOGIN = "Connected user is %s";
    public static final String ERROR_MESSAGE_AUTHORIZATION = "Unauthorized access";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //Récupere le userLogin de la session
        System.out.println(AuthentificationFilter.class.toString());
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //Verifie si protégé
        if(isProtected(httpServletRequest)) {
            HttpSession session  = httpServletRequest.getSession();
            Object userLogin = session.getAttribute(Constants.USER_SESSION_VARIABLE);
            if(userLogin == null) {
                //Throw interrompt le lancement
                throw new ServletException(ERROR_MESSAGE_AUTHORIZATION);
            }
            String stringUserLogin = userLogin.toString();
            System.out.println(String.format(SUCCES_LOGIN, stringUserLogin));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isProtected(HttpServletRequest httpServletRequest) {
        boolean result = false;
        List<String> protectedUris = new ArrayList<String>();
        protectedUris.add("/tp2/protected");
        protectedUris.add("/tp2/api/protected");

        String uri = httpServletRequest.getRequestURI();
        for(String protectedUri : protectedUris) {
            if(uri.startsWith(protectedUri)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public void destroy() {

    }

}
