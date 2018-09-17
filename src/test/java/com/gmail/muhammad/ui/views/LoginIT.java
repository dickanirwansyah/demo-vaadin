package com.gmail.muhammad.ui.views;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import com.gmail.muhammad.AbstractIT;
import com.gmail.muhammad.ui.views.dashboard.DashboardViewElement;

public class LoginIT extends AbstractIT {

	@Test
	public void userIsRedirectedToRequestedView() {
		openLoginView(APP_URL + "#!dashboard").login("barista@vaadin.com", "barista");
		Assert.assertNotNull($(DashboardViewElement.class).first());
	}

	@Test
	public void logoutWorks() {
		loginAsBarista();
		$(MenuElement.class).first().logout();
		Assert.assertEquals("Email", findElement(By.id("login-label")).getText());
	}

}
