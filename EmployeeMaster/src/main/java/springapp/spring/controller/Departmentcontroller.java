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
@RequestMapping("/depmap1")
public class Departmentcontroller {

	@RequestMapping(value = "/depmap1*", method = RequestMethod.GET)
	public ModelAndView depmap1(HttpServletRequest request) throws Exception {
		Model model = new ExtendedModelMap();
		EmplList emplList = new EmplList();
		model.addAttribute("depmap1", emplList);
		return new ModelAndView("/deptset2Depmap1", model.asMap());
	}

	@RequestMapping(value = "/mntmap2*", method = RequestMethod.GET)
	public ModelAndView mntmap2(HttpServletRequest request) throws Exception {
		Model model = new ExtendedModelMap();
		EmplList emplList = new EmplList();
		model.addAttribute("mntmap2", emplList);
		return new ModelAndView("/deptset2Depmap1", model.asMap());
	}

	@RequestMapping(params = "Submit", method = RequestMethod.POST)
	public ModelAndView mntMap1Submit(HttpServletRequest request) throws Exception {

		EmpListMaster empListMaster = new EmpListMaster();
		String wsDeptcode = request.getParameter("wsDeptcode").toUpperCase();
		String actiono = request.getParameter("actiono");
		Model model = new ExtendedModelMap();
		EmplList emplList1 = new EmplList();
		EmpListMaster empListPgm = new EmpListMaster();
		EmpListMaster emplList11 = new EmpListMaster();
		String page = "/deptset2Depmap1";
		LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsDeptcode", wsDeptcode);
		emplList11 = empListMaster.getDepartmentRecord(empListPgm);
		emplList1.setActiono(actiono);
		emplList1.setWsDeptcode(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsDeptcode").toString().toUpperCase());
		emplList1.setWsDeptDesc(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpDeptdesc").toString());
		if (actiono.equalsIgnoreCase("A")) {
			if ((int) LowCodeUtility.getMethod(emplList11.emplistPojo, "responseCode") != 13) {

				emplList1.setMessage("Record already exists");
				model.addAttribute("depmap1", emplList1);
			} else {
				model.addAttribute("depmap1", emplList1);
				// page="/mntset2Mntmap2";
			}
		} else if (actiono.equalsIgnoreCase("C")) {
			if ((int) LowCodeUtility.getMethod(emplList11.emplistPojo, "responseCode") == 13) {
				emplList1.setMessage("Record does not exists");
				model.addAttribute("depmap1", emplList1);
				// page="/mntset2Mntmap1";
			} else {

				model.addAttribute("depmap1", emplList1);
			}

		} else if (actiono.equalsIgnoreCase("D")) {
			if ((int) LowCodeUtility.getMethod(emplList11.emplistPojo, "responseCode") == 13) {
				emplList1.setMessage("Record does not exists");
				model.addAttribute("depmap1", emplList1);
				page = "/deptset2Depmap1";
			} else {
				model.addAttribute("depmap1", emplList1);
			}
		} else {
			emplList1 = new EmplList();
			emplList1.setMessage("Please Enter Values from A or C or D");
			model.addAttribute("depmap1", emplList1);

		}

		return new ModelAndView(page, model.asMap());
	}

	@RequestMapping(params = "Proceed", method = RequestMethod.POST)
	public ModelAndView mntMap2Submit(HttpServletRequest request) throws Exception {
		EmpListMaster empListMaster = new EmpListMaster();
		String wsDeptcode = request.getParameter("wsDeptcode").toUpperCase();
		String actiono = request.getParameter("actiono");
		String wsDeptDesc = request.getParameter("wsDeptDesc");
		Model model = new ExtendedModelMap();
		// Add parameters in model here
		EmplList emplList = new EmplList();
		emplList.setWsDesgcd(wsDeptcode);
		emplList.setActiono(actiono);
		// TODO:Add here function calling from cobol to map manuall
		EmpListMaster empListPgm = new EmpListMaster();
		EmpListMaster emplList11 = new EmpListMaster();
		String page = "/deptset2Depmap1";
		LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsDeptcode", wsDeptcode);
		EmplList emplList1 = new EmplList();
		emplList1.setWsDeptcode(wsDeptcode);
		emplList1.setActiono(actiono);

		if (("A").equalsIgnoreCase(actiono)) {
			LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsDeptcode", wsDeptcode);
			LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpDeptdesc", wsDeptDesc);

			emplList11 = empListMaster.addDepartmentRecord(empListPgm);
			emplList1.setWsDeptDesc(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpDeptdesc").toString());
			emplList1.setMessage("Department record added.");

			model.addAttribute("depmap1", emplList1);
			return new ModelAndView(page, model.asMap());

		}

		if ("C".equalsIgnoreCase(actiono)) {
			LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsDeptcode", wsDeptcode);
			LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpDeptdesc", wsDeptDesc);

			emplList11 = empListMaster.updateDepartmentRecord(empListPgm);
			emplList1.setWsDeptDesc(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpDeptdesc").toString());
			emplList1.setMessage("Department record updated.");

			model.addAttribute("depmap1", emplList1);
			return new ModelAndView(page, model.asMap());
		}

		if ("D".equalsIgnoreCase(actiono)) {
			LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsDeptcode", wsDeptcode);
			emplList11 = empListMaster.deleteDepartmentRecord(empListPgm);

			emplList1.setMessage("Department record deleted.");

			model.addAttribute("depmap1", emplList1);
			return new ModelAndView(page, model.asMap());
		}

		return new ModelAndView(page, model.asMap());
	}

}