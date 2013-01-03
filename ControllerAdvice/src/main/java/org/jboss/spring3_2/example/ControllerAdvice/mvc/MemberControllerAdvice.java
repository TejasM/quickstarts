package org.jboss.spring3_2.example.ControllerAdvice.mvc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jboss.spring3_2.example.ControllerAdvice.domain.Member;
import org.jboss.spring3_2.example.ControllerAdvice.repo.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MemberControllerAdvice {

    @Autowired
    private MemberDao memberDao;
	
	private String getStackTrace(Exception e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		return stringWriter.toString();
	}
	
	
	//Global Exception Handler, removing the need for an extra Exception Handler Controller
	@ExceptionHandler(value = Exception.class)
	public ModelAndView exception(Exception e) {
		ModelAndView model = new ModelAndView("error");
		model.addObject("error", getStackTrace(e));
		return model;
	}

	//Globally adding a date editor for all @RequestMapping methods
	@InitBinder
	public void binder(WebDataBinder dataBinder, WebRequest request) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(format, true);
		dataBinder.registerCustomEditor(Date.class, editor);
	}
	
	//Global Attribute adder for all @RequestMapping methods
	@ModelAttribute("members")
	public List<Member> getMembers(){
        return memberDao.findAllOrderedByName();
	}
}