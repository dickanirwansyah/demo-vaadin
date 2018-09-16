package com.app.view;

import com.app.design.RegisterPerson2;
import com.app.model.Person;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ViewRegisterPerson2 extends RegisterPerson2 implements View {

    //object person
    Person person;

    //binder untuk bind data ke form
    Binder<Person> binderPerson;

    //list untuk menampilkan data person
    List<Person> personList;
    ListDataProvider<Person> personProvider;

    public ViewRegisterPerson2(){
        person = new Person();
        binderPerson = new Binder<>(Person.class);
        personList = new ArrayList<>();

        /** panggil semua method konfigurasi disini **/
        konfigurasiBinder();
        konfigurasiTabelGrid();
        konfigurasiAksiSubmit();
    }

    private void konfigurasiTabelGrid(){
        tabelperson.setSelectionMode(SelectionMode.SINGLE);
        personProvider = DataProvider.ofCollection(personList);
        tabelperson.setDataProvider(personProvider);
    }

    private void konfigurasiAksiSubmit(){
        btnSave.addClickListener(aksi -> {
            if (binderPerson.isValid()){
                (new Notification("berhasil"))
                        .show(getUI().getPage());
                try{

                    binderPerson.writeBean(person);
                    tambahPerson(person);

                }catch (ValidationException ex){
                    ex.printStackTrace();
                }
            }
        });

        tabelperson.addSelectionListener(event -> {
            System.out.println(event.getFirstSelectedItem());
        });
    }


    private void konfigurasiBinder(){

        binderPerson.forField(firstname)
                .withValidator(new StringLengthValidator("5 min, 20 maks", 5, 20))
                .asRequired("tidak boleh kosong")
                .bind(Person::getFirstname, Person::setFirstname);

        binderPerson.forField(lastname)
                .withValidator(new StringLengthValidator("5 min, 20 maks", 5, 20))
                .asRequired("tidak boleh kosong")
                .bind(Person::getLastname, Person::setLastname);

        binderPerson.forField(nickname)
                .withValidator(new StringLengthValidator("5, min, 20 maks", 5, 20))
                .asRequired("tidak boleh kosong")
                .bind(Person::getNickname, Person::setNickname);

        binderPerson.forField(email)
                .withValidator(new EmailValidator("email tidak valid"))
                .asRequired("tidak boleh kosong")
                .bind(Person::getEmail, Person::setEmail);

        binderPerson.forField(password)
                .withValidator(new StringLengthValidator("5 min, 20 maks", 5, 20))
                .asRequired("tidak boleh kosong")
                .bind(Person::getPassword, Person::setPassword);
    }

    private void tambahPerson(Person person){
        personList.add(person);
        personProvider.refreshAll();
    }
}
