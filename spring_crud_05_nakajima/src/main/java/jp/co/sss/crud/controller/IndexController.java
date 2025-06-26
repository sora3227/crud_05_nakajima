package jp.co.sss.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.LoginForm;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class IndexController {
	public static Integer logincheck = 0;
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(@ModelAttribute LoginForm loginForm) {
		//session.invalidate();
		System.out.println("rt");
		
		return "index";
	}


	@RequestMapping(path = "/login", method = RequestMethod.POST)
	//入力チェック実行p3031
	public String index2(@Valid @ModelAttribute LoginForm loginForm, BindingResult result, HttpSession session, Model model) {
		//session.invalidate();
		System.out.println("fe");
		if (result.hasErrors()) {
			System.out.println("error");
			return "index";
		}
		System.out.println(employeeRepository.existsByEmpIdAndEmpPass(loginForm.getEmpId(),loginForm.getEmpPass()));
		if (employeeRepository.existsByEmpIdAndEmpPass(loginForm.getEmpId(),loginForm.getEmpPass())) {
			model.addAttribute("EmpId",loginForm.getEmpId());
			model.addAttribute("EmpPass",loginForm.getEmpPass());
			Employee employee =employeeRepository.findByEmpIdAndEmpPass(loginForm.getEmpId(),loginForm.getEmpPass());
		    session.setAttribute("user", employee);
			model.addAttribute("users",employee);
			//	session.setAttribute("user", employee);
			System.out.println("login");

			List<Employee> employees = employeeRepository.findAll();

			model.addAttribute("employees",employees);
			logincheck = 0;
			//return "Spring_開発演習_HTMLモック_1.3.0/html/list/list";
			return "list/list";

		}
		else {
			model.addAttribute("errMessage", "社員ＩＤ、またはパスワードが間違っています。");
			logincheck++;
			return "index";

		}

	}

	//ログアウト機能
	@RequestMapping(path = "/logout")
	public String logout() {
		session.invalidate();
		System.out.println("logout");
		return "redirect:/";
	}
	

}
