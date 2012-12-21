package org.jboss.spring3_2.example.AsyncRequestMapping.mvc;

import java.util.concurrent.Callable;

import javax.validation.Valid;

import org.jboss.spring3_2.example.AsyncRequestMapping.domain.Member;
import org.jboss.spring3_2.example.AsyncRequestMapping.repo.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class MemberControllerDeferred {
	@Autowired
	private MemberDao memberDao;

	@RequestMapping(method = RequestMethod.GET)
	public String displaySortedMembers(Model model) {
		model.addAttribute("newMember", new Member());
		model.addAttribute("members", memberDao.findAllOrderedByName());
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public Callable<String> registerNewMember(@Valid @ModelAttribute("newMember") final Member newMember, final BindingResult result, final Model model) {
		
		return new Callable<String>() {
			public String call() {
				if (!result.hasErrors()) {
					memberDao.register(newMember);
					return "redirect:/";
				} else {
					model.addAttribute("members", memberDao.findAllOrderedByName());
					return "index";
				}
			}

		};
	}
}
