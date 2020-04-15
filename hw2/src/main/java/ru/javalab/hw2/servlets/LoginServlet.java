package ru.javalab.hw2.servlets;

import org.springframework.context.ApplicationContext;
import ru.javalab.hw2.dto.LoginDto;
import ru.javalab.hw2.services.LoginService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.ftlh").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(loginService.login(LoginDto.builder()
                                        .email(req.getParameter("email"))
                                        .password(req.getParameter("password"))
                                        .build()))
            resp.getWriter().write("Email confirmed");
        else
            resp.getWriter().write("Email not confirmed");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        loginService = springContext.getBean(LoginService.class);
    }
}
