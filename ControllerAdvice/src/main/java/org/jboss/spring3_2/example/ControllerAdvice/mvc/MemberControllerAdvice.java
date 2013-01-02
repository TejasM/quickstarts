package org.jboss.spring3_2.example.ControllerAdvice.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.spring3_2.example.ControllerAdvice.domain.Member;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MemberControllerAdvice {
	@ExceptionHandler(value=Exception.class)
	public Exception exception(){
		return new Exception("Error");
	}
	
	@InitBinder
	public void binder(WebDataBinder dataBinder, WebRequest request){
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		format.setLenient(false);
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}
}

