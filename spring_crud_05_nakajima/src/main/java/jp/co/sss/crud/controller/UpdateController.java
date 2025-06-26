package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class UpdateController {

	public UpdateController() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	HttpSession session;

	/**一件検索*/
	@RequestMapping(path = "/update/input",method = RequestMethod.GET)
	public String updateInput(@RequestParam("empId") Integer empId, Model model) {
		System.out.println("更新");
		//int employeeId = Integer.parseInt(empId);
		Employee employee = new Employee();
		employee=employeeRepository.findByEmpId(empId);
		model.addAttribute("EmployeeForm",employee);
		return "update/update_input";
	}

	/**更新情報チェック*/
	@RequestMapping(path = "/update/check",method = RequestMethod.POST)
	public String updateInput(@Valid @ModelAttribute Employee EmployeeForm,Model model) {
		System.out.println("更新チェック");
		model.addAttribute("EmployeeForm",EmployeeForm);
		return "update/update_check";
	}
}