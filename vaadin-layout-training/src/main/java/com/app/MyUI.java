package com.app;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    /** membuat layout **/

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        setContent(layout);
        /** menambahkan cssLayout**/
        CssLayout topbar = new CssLayout();
        topbar.addStyleName("top");
        Label title = new Label("workshop");
        title.addStyleName("h1");

        topbar.addComponent(title);
        layout.addComponent(topbar);

        /** horizontal layout **/
        HorizontalLayout menuAndContent = new HorizontalLayout();
        menuAndContent.setSizeFull();
        layout.addComponent(menuAndContent);

        CssLayout menu = new CssLayout();
        menu.setWidth("100%");
        menu.addStyleName("menu");
        menuAndContent.addComponent(menu);


        VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        content.setMargin(true);
        menuAndContent.addComponent(content);

        menuAndContent.setExpandRatio(menu, 2);
        menuAndContent.setExpandRatio(content, 8);

        Label header = new Label("Lorem Ipsum");
        header.addStyleName("h2");

        content.addComponent(header);
        Label text = new Label("<p>Belajar Vaadin Layout Templating dengan PT Fusi24com yang " +
                "mengajar mas medina aris</p>", ContentMode.HTML);
        content.addComponent(text);

        FormLayout form = new FormLayout();
        form.setSpacing(true);
        content.addComponent(form);

        TextField txtFirstname = new TextField("Firstname");
        form.addComponent(txtFirstname);
        TextField txtLastname = new TextField("Lastname");
        form.addComponent(txtLastname);
        TextField txtEmail = new TextField("Email");
        form.addComponent(txtEmail);
        TextField txtPhone = new TextField("Phone");
        form.addComponent(txtPhone);

        Button submitButton = new Button("Submit");
        form.addComponent(submitButton);
        

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
