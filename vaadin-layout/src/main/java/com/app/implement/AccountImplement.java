package com.app.implement;

import com.app.design.AccountDesign;
import com.app.model.Acount;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;

import java.util.ArrayList;
import java.util.List;

public class AccountImplement extends AccountDesign implements View {

    public static final String VIEW_NAME = "account";

    Acount acount;
    List<Acount> acounts;
    Binder<Acount> binder;
    ListDataProvider<Acount> dataProvider;

    public AccountImplement(){
        acount = new Acount();
        acounts = new ArrayList<>();
        binder = new Binder<>(Acount.class);

        /** masukan semua method setup kesini **/
        setUpBinder();
        setUpGrid();
        setUpListener();
    }

    private void setUpGrid(){
        accountGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        dataProvider = DataProvider.ofCollection(acounts);
        accountGrid.setDataProvider(dataProvider);
    }

    private void setUpListener(){

        btnSimpan.addClickListener(event -> {
            if (binder.isValid()){
                (new Notification("success")).show(getUI().getPage());
                try{
                    binder.writeBean(acount);
                    process(acount);
                    /** refresh form **/
                    refresh();
                }catch (ValidationException ex){
                    ex.printStackTrace();
                }
            }

        });

        btnRefresh.addClickListener(event -> {
            acount = new Acount();
            binder.readBean(acount);
            txtUsername.focus();
        });

        accountGrid.addSelectionListener(event -> {
            binder.readBean(event.getFirstSelectedItem().get());
        });
    }

    /** lakukan refresh **/
    private void refresh(){
        acount = new Acount();
        binder.readBean(acount);
        txtUsername.focus();;
    }


    private void setUpBinder(){
        binder.forField(txtUsername)
                .withValidator(new StringLengthValidator("5 - 8 Karakter", 5, 8))
                .asRequired()
                .bind(Acount::getUsername, Acount::setUsername);

        binder.forField(txtEmail)
                .withValidator(new EmailValidator("Email not valid"))
                .asRequired()
                .bind(Acount::getEmail, Acount::setEmail);

        binder.forField(txtPassword)
                .withValidator(new StringLengthValidator("5 - 8 Karakter", 5, 8))
                .asRequired()
                .bind(Acount::getPassword, Acount::setPassword);
    }

    private void process(Acount acount){
        acounts.add(acount);
        dataProvider.refreshAll();
    }
}
