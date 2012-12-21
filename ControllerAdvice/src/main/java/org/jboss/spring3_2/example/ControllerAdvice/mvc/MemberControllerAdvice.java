package org.jboss.spring3_2.example.ControllerAdvice.mvc;

import org.jboss.spring3_2.example.ControllerAdvice.domain.Member;
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
		dataBinder.registerCustomEditor(Member.class, "name",new PropertyEditor());
	}
}

