package org.jboss.spring3_2.example.MatrixVariables.mvc;

 import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.jboss.spring3_2.example.MatrixVariables.domain.Member;
import org.jboss.spring3_2.example.MatrixVariables.repo.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/")
public class MemberController
{
    @Autowired
    private MemberDao memberDao;

    @RequestMapping(method=RequestMethod.GET)
    public String displaySortedMembers(Model model)
    {
        model.addAttribute("newMember", new Member());
        model.addAttribute("members", memberDao.findAllOrderedByName());
        return "index";
    }
    

    @RequestMapping( value="/{filter}", method=RequestMethod.GET)
    public ModelAndView filteredMembers(@MatrixVariable(required=false, defaultValue="") String n, @MatrixVariable(required=false, defaultValue="") String e)
    {
    	ModelAndView model = new ModelAndView("index");
    	System.out.println(n);
    	System.out.println(e);
    	model.addObject("newMember", new Member());
        List<Member> members =  memberDao.findAllOrderedByName();
        List<Member> toRemove = new ArrayList<Member>();
        for (Member member : members) {
			if (!member.getName().contains(n) || !member.getEmail().contains(e)){
				toRemove.add(member);
			}
		}
        members.removeAll(toRemove);
        model.addObject("members", members);

        return model;
    }

    @RequestMapping(method=RequestMethod.POST, params="reg")
    public String registerNewMember(@Valid @ModelAttribute("newMember") Member newMember, BindingResult result, Model model)
    {
        if (!result.hasErrors()) {
            memberDao.register(newMember);
            return "redirect:/";
        }
        else {
            model.addAttribute("members", memberDao.findAllOrderedByName());
            return "index";
        }
    }   
}
