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
@RequestMapping("/empmap1")
public class Employeecontroller {

	@RequestMapping(value = "/empmap1*", method = RequestMethod.GET)
	public ModelAndView empmap1(HttpServletRequest request) throws Exception {
		Model model=new ExtendedModelMap();
		EmplList emplList=new EmplList();
		model.addAttribute("empmap1",emplList);
		return new ModelAndView("/empset2Empmap1", model.asMap());
	}
	
	@RequestMapping(value = "/mntmap2*", method = RequestMethod.GET)
	public ModelAndView mntmap2(HttpServletRequest request) throws Exception {
		Model model=new ExtendedModelMap();
		EmplList emplList=new EmplList();
		model.addAttribute("mntmap2",emplList);
		return new ModelAndView("/empset2Empmap1", model.asMap());
	}
	
	//@RequestMapping(value = "/empmap1", method = RequestMethod.POST)
	@RequestMapping(params="Submit", method = RequestMethod.POST)
	public ModelAndView mntMap1Submit(HttpServletRequest request) throws Exception {
		Model model = new ExtendedModelMap();
		String wsEmpId=request.getParameter("wsEmpId").toUpperCase();
		String actiono=request.getParameter("actiono");
		EmplList emplList1=new EmplList();
		EmpListMaster empListMaster=new EmpListMaster();
		String page="/empset2Empmap1";
		
		EmpListMaster empListPgm=new EmpListMaster();
		EmpListMaster emplList11=new EmpListMaster();
		LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsEmpId", wsEmpId);
		emplList11=empListMaster.getEmployeeRecord(empListPgm);
		emplList1.setWsEmpId(wsEmpId);
		emplList1.setActiono(actiono);
		emplList1.setWsDeptcode(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsDeptcode").toString());
		emplList1.setWsEmpId(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpId").toString());
		emplList1.setWsEmpName(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpName").toString());
		emplList1.setWsEmpLoc(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpLoc").toString());
		emplList1.setWsDesgcd(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsDesgcd").toString());
		emplList1.setWsDesgDesc(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpDesgdesc").toString());
		emplList1.setWsEmpBasic(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpBasic").toString());
		emplList1.setWsEmpHra(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpHra").toString());
		emplList1.setWsEmpGrosspay(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpGrosspay").toString());
		emplList1.setWsDeptDesc(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpDeptdesc").toString());
			if(actiono.equalsIgnoreCase("A"))
			{
				if((int)LowCodeUtility.getMethod(emplList11.emplistPojo,"responseCode") != 13){

					emplList1.setMessage("Record already exists");
					model.addAttribute("empmap1",emplList1);
				} else {
					model.addAttribute("empmap1",emplList1);
				}
			}
			else if(actiono.equalsIgnoreCase("C"))
			{
				if((int)LowCodeUtility.getMethod(emplList11.emplistPojo,"responseCode") == 13) {
					emplList1.setMessage("Record doesnot exists");
					model.addAttribute("empmap1",emplList1);
				} else {

					model.addAttribute("empmap1",emplList1);
				}


			}
			else if(actiono.equalsIgnoreCase("D"))
			{
				if((int)LowCodeUtility.getMethod(emplList11.emplistPojo,"responseCode") == 13) {
					emplList1.setMessage("Record doesnot exists");
					model.addAttribute("empmap1",emplList1);
				} else {
					model.addAttribute("empmap1",emplList1);
				}
			}
			else
			{
				emplList1=new EmplList();
				emplList1.setMessage("Please Enter Values from A or C or D");
				model.addAttribute("empmap1",emplList1);

			}
		return new ModelAndView(page, model.asMap());
	}
	@RequestMapping(params="Proceed", method = RequestMethod.POST)
	public ModelAndView mntMap11Submit(HttpServletRequest request) throws Exception {
		Model model = new ExtendedModelMap();
		String wsEmpId=request.getParameter("wsEmpId").toUpperCase();
		String wsEmpName=request.getParameter("wsEmpName");
		String wsEmpLoc=request.getParameter("wsEmpLoc");
		String wsDeptcode=request.getParameter("wsDeptcode");
		String actiono=request.getParameter("actiono");
		String wsDesgcd=request.getParameter("wsDesgcd");
	
		EmpListMaster empListMaster=new EmpListMaster();
		String page="/empset2Empmap1";
		EmplList emplList=new EmplList();
		emplList.setWsEmpId(wsEmpId);
		emplList.setActiono(actiono);
		EmplList emplList1=new EmplList();
		EmpListMaster empListPgm=new EmpListMaster();
		EmpListMaster emplList11=new EmpListMaster();
		LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsEmpId", wsEmpId);
		emplList1.setWsEmpId(wsEmpId);
		emplList1.setActiono(actiono);
			
			 page="/empset2Empmap1";
			if(("A").equalsIgnoreCase(actiono)) {
				LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsDesgcd", wsDesgcd);
				LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsDeptcode", wsDeptcode);
				LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsEmpId", wsEmpId);
				LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsEmpName", wsEmpName);
				LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsEmpLoc", wsEmpLoc);
				
				emplList11=empListMaster.addEmployeeRecord(empListPgm);
				
				emplList1.setWsDeptcode(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsDeptcode").toString());
				emplList1.setWsEmpId(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpId").toString().toUpperCase());
				emplList1.setWsEmpName(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpName").toString());
				emplList1.setWsEmpLoc(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpLoc").toString());
				emplList1.setWsDesgcd(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsDesgcd").toString());
				emplList1.setWsDesgDesc(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpDesgdesc").toString());
				emplList1.setWsEmpBasic(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpBasic").toString());
				emplList1.setWsEmpHra(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpHra").toString());
				emplList1.setWsEmpGrosspay(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpGrosspay").toString());
				emplList1.setWsDeptDesc(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpDeptdesc").toString());
			
				
				emplList1.setMessage("Employee record added.");
				
				model.addAttribute("empmap1",emplList1);
				return new ModelAndView(page, model.asMap());
				
			}
			
			if("C".equalsIgnoreCase(actiono)) {
				LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsDesgcd", wsDesgcd);
				LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsDeptcode", wsDeptcode);
				LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsEmpId", wsEmpId);
				LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsEmpName", wsEmpName);
				LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsEmpLoc", wsEmpLoc);
				
				
				emplList11=empListMaster.updateEmployeeRecord(empListPgm);
				emplList1.setWsDeptcode(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsDeptcode").toString());
				emplList1.setWsEmpId(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpId").toString().toUpperCase());
				emplList1.setWsEmpName(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpName").toString());
				emplList1.setWsEmpLoc(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpLoc").toString());
				emplList1.setWsDesgcd(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsDesgcd").toString());
				emplList1.setWsDesgDesc(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpDesgdesc").toString());
				emplList1.setWsEmpBasic(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpBasic").toString());
				emplList1.setWsEmpHra(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpHra").toString());
				emplList1.setWsEmpGrosspay(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpGrosspay").toString());
				emplList1.setWsDeptDesc(LowCodeUtility.getMethod(emplList11.emplistPojo,"wsEmpDeptdesc").toString());
			
				emplList1.setMessage("Employee record updated.");
				model.addAttribute("empmap1",emplList1);
				return new ModelAndView(page, model.asMap());
			}
			
			if("D".equalsIgnoreCase(actiono)) {
				LowCodeUtility.setMethod(empListPgm.emplistPojo,"wsEmpId", wsEmpId);
				
				
				emplList11=empListMaster.deleteEmployeeRecord(empListPgm);
				emplList1.setMessage("Employee record deleted.");
				
				model.addAttribute("empmap1",emplList1);
				return new ModelAndView(page, model.asMap());
			}
			
			
		return new ModelAndView(page, model.asMap());
	}
	

}
