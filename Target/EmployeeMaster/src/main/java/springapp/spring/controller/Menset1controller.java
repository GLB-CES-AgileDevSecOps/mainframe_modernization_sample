package springapp.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import springapp.spring.copybook.EmplList;


@Controller
@RequestMapping("/empmenu")
public class Menset1controller {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView empmenu(HttpServletRequest request) throws Exception {
		Model model=new ExtendedModelMap();
		EmplList emplList=new EmplList();
		model.addAttribute("empmenu",emplList);
		return new ModelAndView("/empset1Empmenu", model.asMap());
	}

	@RequestMapping(params="Cancel", method = RequestMethod.POST)
	public ModelAndView cancelAction(HttpServletRequest request) throws Exception {

		Model model=new ExtendedModelMap();
		EmplList emplList=new EmplList();
		model.addAttribute("empmenu",emplList);
		return new ModelAndView("/empset1Empmenu", model.asMap());
	}
	@RequestMapping(params="Exit", method = RequestMethod.POST)
	public ModelAndView exitAction(HttpServletRequest request) throws Exception {
		Model model=new ExtendedModelMap();
		EmplList emplList=new EmplList();
		model.addAttribute("empmenu",emplList);
		return new ModelAndView("/empset1Empmenu", model.asMap());
	}
	@RequestMapping(params="Submit", method = RequestMethod.POST)
	public ModelAndView menmap11(HttpServletRequest request) throws Exception {
		String actiono=request.getParameter("actiono");
		EmplList emplList=new EmplList();
		Model model = new ExtendedModelMap();
		String page="/empset1Empmenu";
		if(actiono.equals("1"))
		{
			model.addAttribute("depmap1",emplList);
			page="deptset2Depmap1";
			
		}
		else if(actiono.equals("2"))
		{
			//desgset2Desgmap1
			page="/desgset2Desgmap1";
			model.addAttribute("desgmap1",emplList);
		}
		else if(actiono.equals("3"))
		{
			page="empset2Empmap1";
			model.addAttribute("empmap1",emplList);
		}
		
		else
		{
			emplList.setMessage("Please Enter Values from 1 OR 2 OR 3");
			model.addAttribute("empmenu",emplList);
		}


		return new ModelAndView(page, model.asMap());
	}

}
