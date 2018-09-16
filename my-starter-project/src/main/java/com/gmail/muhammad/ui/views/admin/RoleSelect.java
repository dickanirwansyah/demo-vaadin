package com.gmail.muhammad.ui.views.admin;

import com.gmail.muhammad.backend.data.Role;
import com.vaadin.ui.ComboBox;

public class RoleSelect extends ComboBox<String> {

	public RoleSelect() {
		setCaption("Role");
		setEmptySelectionAllowed(false);
		setItems(Role.getAllRoles());
		setTextInputAllowed(false);
	}
}
