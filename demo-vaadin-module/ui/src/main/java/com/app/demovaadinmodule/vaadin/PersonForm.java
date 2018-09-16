package com.app.demovaadinmodule.vaadin;


import com.app.demovaadinmodule.model.Person;
import com.app.demovaadinmodule.repository.PersonRepository;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.vaadin.spring.events.EventBus;
import org.vaadin.teemu.switchui.Switch;
import org.vaadin.viritin.fields.EmailField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

@UIScope
@SpringComponent
public class PersonForm extends AbstractForm<Person>{

    private static final long serialVersionUID = 1L;

    EventBus.UIEventBus eventBus;
    PersonRepository personRepository;

    /** declare textfield **/
    TextField firstname = new MTextField("Firstname");
    TextField lastname = new MTextField("Lastname");
    EmailField email = new EmailField("Email");
    DateField birthday = new DateField("Birthday");
    ComboBox<String> comboBox = new ComboBox("Coba");
    TextField address = new TextField("Address");
    Switch active = new Switch("Active");

    public PersonForm(PersonRepository pr, EventBus.UIEventBus eb){
        super(Person.class);
        this.personRepository = pr;
        this.eventBus = eb;

        this.comboBox.setItems("Laki-Laki", "Wanita");

        this.comboBox.addValueChangeListener(event -> {
            if (event.getSource().isEmpty()){
                Notification.show("selected one");
            }else {
                Notification.show("selected gender "+event.getValue());
            }
        });

        /** handling save **/
        setSavedHandler(person -> {
            personRepository.save(person);
            eventBus.publish(this, new PersonModifiedEvent(person));
        });
        setResetHandler(p -> eventBus.publish(this, new PersonModifiedEvent(p)));
        setSizeUndefined();
    }

    @Override
    protected void bind() {
        getBinder()
                .forMemberField(birthday)
                .withConverter(new LocalDateToDateConverter());
        super.bind();
    }

    @Override
    protected Component createContent() {
       return new MVerticalLayout(
               new MFormLayout(
                       firstname,
                       lastname,
                       email,
                       birthday,
                       comboBox,
                       address,
                       active)
                .withWidth(""),
        getToolbar()
       ).withWidth("");
    }
}
