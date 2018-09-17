package com.app.implement;

import com.app.design.HomePageDesign;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

import java.util.Iterator;


public class HomePageView extends HomePageDesign implements ViewDisplay {

    private static final String STYLE_SELECTED = "selected";
    Navigator navigator;

    public HomePageView(){

        Button btnPage1 = new Button("Page 1");
        btnPage1.setStyleName(sampleButton.getStyleName());
        Button btnPage2 = new Button("Page 2");
        btnPage2.setStyleName(sampleButton.getStyleName());
        Button btnPage3 = new Button("Account");
        btnPage3.setStyleName(sampleButton.getStyleName());


        navigator = new Navigator(getUI().getCurrent(), (ViewDisplay) this);
        addNavigatorView(Page1View.VIEW_NAME, Page1View.class, btnPage1);
        addNavigatorView(Page2View.VIEW_NAME, Page2View.class, btnPage2);
        addNavigatorView(AccountImplement.VIEW_NAME, AccountImplement.class, btnPage3);

        navLayout.removeAllComponents();
        navLayout.addComponents(btnPage1, btnPage2, btnPage3);
    }

    private void doNavigate(String viewName){
        getUI().getNavigator().navigateTo(viewName);
    }

    private void addNavigatorView(String viewName, Class<? extends View> viewClass, Button menuButton){
        navigator.addView(viewName, viewClass);
        menuButton.addClickListener(e -> {
            doNavigate(viewName);
        });
        menuButton.setData(viewClass.getName());
    }

    private void addJustStyleByData(Component component, Object data){
        if (component instanceof Button){
            if (data != null && data.equals(((Button) component).getData())){
                component.addStyleName(STYLE_SELECTED);
            }else {
                component.removeStyleName(STYLE_SELECTED);
            }
        }
    }

    @Override
    public void showView(View view) {
        if (view instanceof Component){
            viewPanel.setContent((Component) view);
            Iterator<Component> it = navLayout.iterator();
            while(it.hasNext()){
                addJustStyleByData(it.next(), view.getClass().getName());
            }
        }else{
            throw new IllegalArgumentException("View is not a component");
        }
    }
}
