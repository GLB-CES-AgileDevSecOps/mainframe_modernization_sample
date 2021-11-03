package springapp.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import springapp.spring.copybook.EmplList;
import springapp.spring.program.EmpListMaster;
import springapp.spring.util.LowCodeUtility;

@Controller
@RequestMapping("/desgmap1")
public class DesignationController {
	
	@RequestMapping(value = "/desgmap1*", method = RequestMethod.GET)
	public ModelAndView desgmap1(HttpServletRequest request) throws Exception {
		Model model=new ExtendedModelMap();
		EmplList emplList=new EmplList();
		//Mntset2Fields mntset2fields=new Mntset2Fields();
		model.addAttribute("desgmap1",emplList);
		return new ModelAndView("/desgset2Desgmap1", model.asMap());
	}
	
	@RequestMapping(value = "/mntmap2*", method = RequestMethod.GET)
	public ModelAndView mntmap2(HttpServletRequest request) throws Exception {
		Model model=new ExtendedModelMap();
		EmplList emplList=new EmplList();
		model.addAttribute("mntmap2",emplList);
		return new ModelAndView("/desgset2Desgmap1", model.asMap());
	}
	
	@RequestMapping(params="Submit", method = RequestMethod.POST)
	public ModelAndView mntMap1Submit(HttpServletRequest request) throws Exception {
		
		String temp="desgmap1";
		String actiono=request.getParameter("actiono");
		String wsDesgcd=request.getParameter("wsDesgcd").toUpperCase();
		Model model = new ExtendedModelMap();
		EmplList emplList1=new EmplList();
		EmpListMaster empListPgm=new EmpListMaster();
		EmpListMaster emplList11=new EmpListMaster();
		LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsDesgcd", wsDesgcd);
		String page="/desgset2Desgmap1";
		EmpListMaster empListMaster=new EmpListMaster();
		emplList11=empListMaster.getDesignationRecord(empListPgm);
		emplList1.setWsDesgcd(wsDesgcd);
		emplList1.setActiono(actiono);
		emplList1.setWsDesgDesc(LowCodeUtility.getMethod(empListPgm.emplistPojo,"wsEmpDesgdesc").toString());
		emplList1.setWsEmpBasic(LowCodeUtility.getMethod(empListPgm.emplistPojo,"wsEmpBasic").toString());
		
		emplList1.setWsEmpHra(LowCodeUtility.getMethod(empListPgm.emplistPojo,"wsEmpHra").toString());
		emplList1.setWsEmpGrosspay(LowCodeUtility.getMethod(empListPgm.emplistPojo,"wsEmpGrosspay").toString());
			if(actiono.equalsIgnoreCase("A"))
			{
				if((int)LowCodeUtility.getMethod(emplList11.emplistPojo,"responseCode") != 13){

					emplList1.setMessage("Record already exists");
					model.addAttribute(temp,emplList1);
				} else {
					model.addAttribute(temp,emplList1);
				}
			}
			else if(actiono.equalsIgnoreCase("C"))
			{
				if((int)LowCodeUtility.getMethod(emplList11.emplistPojo,"responseCode") == 13) {
					emplList1.setMessage("Record doesnot exists");
					model.addAttribute(temp,emplList1);
				} else {

					model.addAttribute(temp,emplList1);
				}


			}
			else if(actiono.equalsIgnoreCase("D"))
			{
				if((int)LowCodeUtility.getMethod(emplList11.emplistPojo,"responseCode") == 13) {
					emplList1.setMessage("Record doesnot exists");
					model.addAttribute(temp,emplList1);
				} else {
					model.addAttribute(temp,emplList1);
				}
			}
			else
			{
				emplList1=new EmplList();
				emplList1.setMessage("Please Enter Values from A or C or D");
				model.addAttribute(temp,emplList1);

			}
		
			
			
		return new ModelAndView(page, model.asMap());
	}
	
	@RequestMapping(params="Proceed", method = RequestMethod.POST)
	public ModelAndView mntMap2Submit(HttpServletRequest request) throws Exception {
		
		String wsDesgDesc=request.getParameter("wsDesgDesc");
		String wsEmpBasic=request.getParameter("wsEmpBasic");
		String wsEmpHra=request.getParameter("wsEmpHra");
		String wsDesgcd=request.getParameter("wsDesgcd").toUpperCase();
		String actiono=request.getParameter("actiono");
		String temp="desgmap1";
		Model model = new ExtendedModelMap();
		EmplList emplList1=new EmplList();
		String page="/desgset2Desgmap1";
		EmpListMaster empListMaster=new EmpListMaster();
		EmpListMaster empListPgm=new EmpListMaster();
		EmpListMaster emplList11=new EmpListMaster();
		LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsDesgcd", wsDesgcd);
		emplList1.setActiono(actiono);
		emplList1.setWsDesgcd(wsDesgcd);
		if(("A").equalsIgnoreCase(actiono)) {
			LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsDesgcd", wsDesgcd);
			LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsEmpDesgdesc", wsDesgDesc);
			LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsEmpBasic", wsEmpBasic);
			LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsEmpHra", wsEmpHra);
			emplList11=empListMaster.addDesignationRecord(empListPgm);
			
			emplList1.setWsDesgDesc(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpDesgdesc").toString());
			emplList1.setWsEmpBasic(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpBasic").toString());
			emplList1.setWsEmpHra(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpHra").toString());
			emplList1.setWsEmpGrosspay(String.valueOf(Integer.parseInt(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpBasic").toString())+Integer.parseInt(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpHra").toString())));
			
			emplList1.setMessage("Designation record added.");
			
			model.addAttribute(temp,emplList1);
			return new ModelAndView(page, model.asMap());
			
		}
		
		if("C".equalsIgnoreCase(actiono)) {
			LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsDesgcd", wsDesgcd);
			LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsEmpDesgdesc", wsDesgDesc);
			LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsEmpBasic", wsEmpBasic);
			LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsEmpHra", wsEmpHra);
				
			emplList11=empListMaster.updateDesignationRecord(empListPgm);
			emplList1.setWsDesgDesc(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpDesgdesc").toString());
			emplList1.setWsEmpBasic(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpBasic").toString());
			emplList1.setWsEmpHra(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpHra").toString());
			emplList1.setWsEmpGrosspay(String.valueOf(Integer.parseInt(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpBasic").toString())+Integer.parseInt(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpHra").toString())));
			
			
			emplList1.setMessage("Designation record updated.");
			
			model.addAttribute(temp,emplList1);
			return new ModelAndView(page, model.asMap());
		}
		
		if("D".equalsIgnoreCase(actiono)) {
			LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsDesgcd", wsDesgcd);
			emplList11=empListMaster.deleteDesignationRecord(empListPgm);
			
			
			emplList1.setMessage("Designation record deleted.");
			
			model.addAttribute(temp,emplList1);
			return new ModelAndView(page, model.asMap());
		}
		
		return new ModelAndView(page, model.asMap());
	}



}
