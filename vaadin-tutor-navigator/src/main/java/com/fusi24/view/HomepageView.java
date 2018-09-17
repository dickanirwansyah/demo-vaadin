package com.fusi24.view;

import java.util.Iterator;

import com.fusi24.design.HomepageDesign;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

public class HomepageView extends HomepageDesign implements ViewDisplay{
	
	private static final String STYLE_SELECTED = "selected";
	Navigator nav;
	
	public HomepageView() {
		
		Button btn1 = new Button("Page 1");
		btn1.setStyleName(sampleButton.getStyleName());
		Button btn2 = new Button("Page 2");
		btn2.setStyleName(sampleButton.getStyleName());
		
		
		nav = new Navigator(getUI().getCurrent(), (ViewDisplay) this);
		addNavigatorView(Page1View.VIEW_NAME, Page1View.class, btn1);
		addNavigatorView(Page2View.VIEW_NAME, Page2View.class, btn2);
				
		
		navLayout.removeAllComponents();
		navLayout.addComponents(btn1, btn2);
	}
	
	private void doNavigate(String viewName) {
        getUI().getNavigator().navigateTo(viewName);
    }

    private void addNavigatorView(String viewName,
            Class<? extends View> viewClass, Button menuButton) {
        nav.addView(viewName, viewClass);
        menuButton.addClickListener(event -> doNavigate(viewName));
        menuButton.setData(viewClass.getName());
    }
    
    private void adjustStyleByData(Component component, Object data) {
        if (component instanceof Button) {
            if (data != null && data.equals(((Button) component).getData())) {
                component.addStyleName(STYLE_SELECTED);
            } else {
                component.removeStyleName(STYLE_SELECTED);
            }
        }
    }

	@Override
	public void showView(View view) {
		  if (view instanceof Component) {
			    viewPanel.setContent((Component) view);
	            Iterator<Component> it = navLayout.iterator();
	            while (it.hasNext()) {
	                adjustStyleByData(it.next(), view.getClass().getName());
	            }
	        } else {
	            throw new IllegalArgumentException("View is not a Component");
	        }
		
	}
}
