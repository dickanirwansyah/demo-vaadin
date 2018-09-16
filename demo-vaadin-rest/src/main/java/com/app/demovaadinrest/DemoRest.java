package com.app.demovaadinrest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/rest")
public class DemoRest {

    @GetMapping
    public String exampleRest(){
        return "REST works!<br/><a href='/servlet'>Servlet</a><br/><a href='/'>Vaadin</a>";
    }
}
