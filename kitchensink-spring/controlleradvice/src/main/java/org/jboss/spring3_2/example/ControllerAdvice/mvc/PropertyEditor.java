package org.jboss.spring3_2.example.ControllerAdvice.mvc;

import java.beans.PropertyEditorSupport;

public class PropertyEditor extends PropertyEditorSupport{
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue("Tejas Mehta");
	}
}
