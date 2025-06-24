package jp.co.sss.crud.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.repository.EmployeeRepository;
@Controller
public class RegistrationController {

	public RegistrationController() {
		// TODO 自動生成されたコンストラクター・スタブ
		
		
	}
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	HttpSession session;
	
	//新規社員登録
	@RequestMapping(path = "regist/input")
	public String insert(Model model) {
		System.out.println("新規登録");
		Employee employee = new Employee();
		model.addAttribute("EmployeeForm",employee);
		return "regist/regist_input";
		//return "redirect:Spring_開発演習_HTMLモック_1.3.0/html/list/list";
	}
	
	//新規社員登録
		@RequestMapping(path = "regist/input", method=RequestMethod.POST)
		public String reinsert(@ModelAttribute Employee EmployeeForm, Model model) {
			System.out.println("再入力");
			model.addAttribute("EmployeeForm",EmployeeForm);
			return "regist/regist_input";
			//return "redirect:Spring_開発演習_HTMLモック_1.3.0/html/list/list";
		}

	//新規社員登録チェック
	@RequestMapping(path = "regist/check",method=RequestMethod.POST)
	public String insertcheck(@Valid @ModelAttribute Employee EmployeeForm,BindingResult result, Model model) {
		
		System.out.println("新規登録チェック");
		System.out.println("name" + EmployeeForm.getEmpPass());
		System.out.println("gender" + EmployeeForm.getGender());
		System.out.println("name" + EmployeeForm.getEmpName());
		System.out.println("address" + EmployeeForm.getAddress());
		System.out.println("birth" + EmployeeForm.getBirthday());
		System.out.println("権限" + EmployeeForm.getAuthority());
	//	System.out.println("name" + EmployeeForm.getDepartment());
	
		model.addAttribute("EmployeeForm",EmployeeForm);
		System.out.println("clear");
		return "regist/regist_check";
		//return "redirect:Spring_開発演習_HTMLモック_1.3.0/html/list/list";
	}	
	
	//新規社員登録チェック
		@RequestMapping(path = "regist/conplete",method=RequestMethod.POST)
		public String insertcomplete(@Valid @ModelAttribute Employee EmployeeForm,BindingResult result, Model model) {
			model.addAttribute("EmployeeForm",EmployeeForm);
			Employee employee = new Employee();
			BeanUtils.copyProperties(EmployeeForm,  employee, "empId");
			employee = employeeRepository.save(employee);
			EmployeeBean employeeBean = new EmployeeBean();
			BeanUtils.copyProperties(employee, employeeBean);
			model.addAttribute("EmployeeForm",employeeBean);
			
			return "regist/regist_complete";
			//return "redirect:Spring_開発演習_HTMLモック_1.3.0/html/list/list";
		}
}
