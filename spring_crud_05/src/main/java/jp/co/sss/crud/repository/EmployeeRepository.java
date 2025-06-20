package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.crud.entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findByEmpId(Integer empId);
	List<Employee> findByEmpIdAndEmpPass(Integer empId, String password);
	boolean existsByEmpPass(String empPass);
	boolean existsByEmpId(Integer empId);
	boolean existsByEmpIdAndEmpPass(Integer empId, String empPass);
	Object findByEmpNameContaining(String empName);
	
}
