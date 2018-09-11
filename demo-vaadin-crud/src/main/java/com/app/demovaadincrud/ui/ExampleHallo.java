package com.app.demovaadincrud.ui;

import com.app.demovaadincrud.entity.Person;
import com.app.demovaadincrud.service.PersonService;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@SpringUI
@Theme("valo")
@Title("Hello Vaadin")
public class ExampleHallo extends UI{

    @Autowired
    PersonService personService;

    private Grid<Person> gridPerson;

    @Override
    protected void init(VaadinRequest request){
        VerticalLayout verticalLayout = new VerticalLayout();
        setContent(verticalLayout);

        FormLayout formLayout = new FormLayout();

        TextField textFieldName = new TextField("Name");
        textFieldName.setRequiredIndicatorVisible(true);
        textFieldName.setIcon(VaadinIcons.USER);
        textFieldName.setMaxLength(50);

        final TextField textFieldAge = new TextField("Age");
        textFieldAge.setRequiredIndicatorVisible(true);
        textFieldAge.setIcon(VaadinIcons.BELL);
        textFieldAge.setMaxLength(2);

        DateField dateField = new DateField("Date");
        dateField.setDateFormat("yyyy-MM-dd");
        dateField.setPlaceholder("yyyy-mm-dd");
        dateField.setRequiredIndicatorVisible(true);
        dateField.setIcon(VaadinIcons.DATE_INPUT);
        dateField.setValue(LocalDate.now());

        Button buttonSubmit = new Button("Submit");

        formLayout.addComponent(textFieldName);
        formLayout.addComponent(textFieldAge);
        formLayout.addComponent(dateField);
        formLayout.addComponent(buttonSubmit);

        formLayout.setWidth(null);
        verticalLayout.addComponent(formLayout);


        /** grid view **/
        this.gridPerson = new Grid<>();
        gridPerson.addColumn(Person::getName).setCaption("Name of person");
        gridPerson.addColumn(Person::getAge).setCaption("Age of person");
        gridPerson.addColumn(Person::getDateOfBirth).setCaption("Birthday of person");
        verticalLayout.addComponent(gridPerson);
        listPersons();
        /** grid view **/


        /** button submit **/
        buttonSubmit.addClickListener(click -> {
            if (isValidAge(textFieldAge)){
                Person person = personService.create(
                        new Person(textFieldName.getValue(),
                                Integer.valueOf(textFieldAge.getValue()),
                                dateField.getValue()));

                textFieldName.setValue("");
                textFieldAge.setValue("");
                dateField.setValue(LocalDate.now());
                listPersons();

                Notification.show("data saved, person id is : "+person.getId());
            }else {
                Notification.show("data failed to saved.");
            }
        });

    }

    private void listPersons(){
        gridPerson.setItems(personService.list());
    }


    private boolean isValidAge(TextField textFieldAge){
        boolean isValid = true;

        try{
            String ageValue = textFieldAge.getValue();
            int age = Integer.valueOf(ageValue);
        }catch (Exception e){
            e.printStackTrace();
            isValid=false;
        }
        return isValid;
    }

}
