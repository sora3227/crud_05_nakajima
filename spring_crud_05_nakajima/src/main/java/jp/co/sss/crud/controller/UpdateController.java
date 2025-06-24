package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
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
	@RequestMapping(path = "/update/input/{empId}",method = RequestMethod.GET)
	public String updateInput(@ModelAttribute Integer empId, Model model) {
		System.out.println("更新");
		//int employeeId = Integer.parseInt(empId);
		Employee employee = new Employee();
		employee=employeeRepository.findByEmpId(empId);
		model.addAttribute("EmployeeForm",employee);
		return "update/update_check";
	}

}