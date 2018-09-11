package com.spring.app.demovaadins.vaadin.ui;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.spring.app.demovaadins.backend.entity.Product;
import com.spring.app.demovaadins.backend.repository.ProductRepository;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import org.vaadin.spring.events.EventBus;
import org.vaadin.teemu.switchui.Switch;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;



@UIScope
@SpringComponent
public class ProductForm extends AbstractForm<Product>{

    private static final long serialVersionUID = 1L;

    EventBus.UIEventBus eventBus;
    ProductRepository repo;

    TextField name = new MTextField("Name");
    IntegerField stock = new IntegerField("Stock");
    IntegerField price = new IntegerField("Price");
    DateField createdAt = new DateField("Created");
    Switch colleague = new Switch("Colleague");



    ProductForm(ProductRepository r, EventBus.UIEventBus b) {
        super(Product.class);
        this.repo = r;
        this.eventBus = b;

        setSavedHandler(product -> {
            repo.save(product);
            eventBus.publish(this, new ProductModifiedEvent(product));

        });
        setResetHandler(p -> eventBus.publish(this, new ProductModifiedEvent(p)));
        setSizeUndefined();
    }


    @Override
    protected void bind() {
        getBinder()
                .forMemberField(createdAt)
                .withConverter(new LocalDateToDateConverter());
        super.bind();
    }

    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new MFormLayout(
                        name,
                        price,
                        stock,
                        createdAt,
                        colleague
                ).withWidth(""),
                getToolbar()
        ).withWidth("");
    }
}
