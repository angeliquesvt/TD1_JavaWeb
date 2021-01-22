package com.esiee.tp2.servlet;

import com.esiee.tp2.Constants;
import com.esiee.tp2.domain.Person;
import com.esiee.tp2.model.Datamodel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthentificationServlet extends HttpServlet {

    public static final String USERNAME_VARIABLE = "username";
    public static final String PASSWORD_VARIABLE = "password";
    public static final String ERROR_MESSAGE = "user or password not correct";
    public static final String PARAMETER_IS_MISSING = "Variable '%s' is required";
    public static final String USER_NOT_FOUND = "User %s not found";
    public static final String PASSWORD_NOT_VALID = "Password %s not valid";

    private Datamodel datamodel = Datamodel.getInstance();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(AuthentificationServlet.class.toString());

        String username = request.getParameter(USERNAME_VARIABLE);
        String password = request.getParameter(PASSWORD_VARIABLE);

        if(username == null || username.isEmpty()) {
            System.out.println(String.format(PARAMETER_IS_MISSING, USERNAME_VARIABLE));
            throw new ServletException(ERROR_MESSAGE);
        }

        if(password == null || password.isEmpty()) {
            System.out.println(String.format(PARAMETER_IS_MISSING, PASSWORD_VARIABLE));
            throw new ServletException(ERROR_MESSAGE);
        }

        Person person = datamodel.getPersonByLogin(username);
        if(person == null) {
            System.out.println(String.format(USER_NOT_FOUND, username));
            throw new ServletException(ERROR_MESSAGE);
        }

        if(!password.equals(person.getPassword())) {
            System.out.println(String.format(PASSWORD_NOT_VALID, password));
            throw new ServletException(ERROR_MESSAGE);
        }

        request.getSession().setAttribute(Constants.USER_SESSION_VARIABLE, username);
        response.sendRedirect(String.format("%s/%s", request.getContextPath(),SUCCES_URL));
    }
}
