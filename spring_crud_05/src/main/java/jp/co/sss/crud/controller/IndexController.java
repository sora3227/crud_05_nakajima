package jp.co.sss.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.LoginForm;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class IndexController {
	
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
	public String index2(@Valid @ModelAttribute LoginForm loginForm, BindingResult result, HttpSession session) {
		//session.invalidate();
		System.out.println("fe");
		session.setAttribute("userId",loginForm.getEmpId());
		session.setAttribute("userpass", loginForm.getEmpPass());
		List<Employee> employee =repository.findByEmpId(loginForm.getEmpId());
		EmployeeBean employeebean = new EmployeeBean();
		
		if (result.hasErrors()) {
			return "form/LoginForm";
		}
		return "Spring_開発演習_HTMLモック_1.3.0/html/list/list";
	}

	//ログアウト機能
	@RequestMapping(path = "/logout")
	public String logout() {
		session.invalidate();
		System.out.println("logout");
		return "redirect:/";
	}
}
