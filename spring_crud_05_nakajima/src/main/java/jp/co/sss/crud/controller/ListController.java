package jp.co.sss.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class ListController {

	public ListController() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	HttpSession session;

	//一覧表示へ戻る
	@RequestMapping(path = "/login")
	public String reload(Model model) {
		System.out.println("リロード");
		List<Employee> employees =employeeRepository.findAll();
		model.addAttribute("employees",employees);
		return "list/list";
	}

	//社員名あいまい検索
	@RequestMapping(path = "list/empName",method = RequestMethod.GET)
	public String searchName(@RequestParam String empName, Model model) {
		System.out.println("社員名");
		List<Employee> employees = employeeRepository.findByEmpNameContaining(empName);
		session.invalidate();
		model.addAttribute("employees",employees);
		model.addAttribute("back_to_top_message", "該当する社員は存在しません。");
		System.out.println(empName);

		//model.addAttribute("employees", employeeRepository.findByEmpNameContaining(empName));

		return "list/list";
		//return "redirect:Spring_開発演習_HTMLモック_1.3.0/html/list/list";
	}

	//部署名検索
	@RequestMapping(path = "list/deptId",method = RequestMethod.GET)
	public String searchAuthority(@RequestParam Integer deptId, Model model) {
		System.out.println("部署名検索");
		List<Employee> employees = employeeRepository.findByDeptId(deptId);
		model.addAttribute("employees",employees);
		System.out.println(deptId);
		model.addAttribute("back_to_top_message", "該当する社員は存在しません。");
		return "list/list";
		//return "redirect:Spring_開発演習_HTMLモック_1.3.0/html/list/list";
	}
}
