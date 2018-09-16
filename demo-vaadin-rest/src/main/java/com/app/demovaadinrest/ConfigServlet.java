package com.app.demovaadinrest;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class ConfigServlet {

    @Bean
    public ServletRegistrationBean servlet(){
        return new ServletRegistrationBean(new HttpServlet() {

            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                resp.setContentType("text/html");
                resp.getWriter()
                        .append("Servlet works!<br/><a href='/'>Vaadin</a><a href='/rest'>REST</a><br/>");
            }
        }, "/servlet/*");
    }
}
