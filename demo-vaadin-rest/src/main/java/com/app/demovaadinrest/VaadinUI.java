package com.app.demovaadinrest;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI{

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(new Label("Vaadin works!<br/><a href='/rest'>REST</a><br/><a href='/servlet'>Servlet</a>",
                ContentMode.HTML));
    }
}
