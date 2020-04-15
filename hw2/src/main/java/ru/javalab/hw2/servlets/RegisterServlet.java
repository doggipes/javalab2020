package ru.javalab.hw2.servlets;

import freemarker.template.TemplateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.javalab.hw2.dto.UserDto;
import ru.javalab.hw2.services.RegisterService;

import javax.mail.MessagingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    RegisterService registerService;
    FreeMarkerConfigurer freeMarkerConfig;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        registerService = springContext.getBean(RegisterService.class);
        freeMarkerConfig = springContext.getBean(FreeMarkerConfigurer.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.ftlh").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = registerService.signUp(UserDto.builder()
                .email(req.getParameter("email"))
                .name(req.getParameter("name"))
                .password(req.getParameter("password"))
                .build());
        if(userDto != null){
            try {
                registerService.sendEmail(userDto);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
    }
}
