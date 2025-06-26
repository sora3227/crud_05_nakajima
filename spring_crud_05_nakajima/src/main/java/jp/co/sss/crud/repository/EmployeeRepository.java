package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.crud.entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findByEmpId(Integer empId);
	Employee findByEmpIdAndEmpPass(Integer empId, String password);
	boolean existsByEmpPass(String empPass);
	boolean existsByEmpId(Integer empId);
	boolean existsByEmpIdAndEmpPass(Integer empId, String empPass);
	List<Employee> findByEmpNameContaining(String empName);
	List<Employee> findByEmpIdContaining(Integer empId);
	@Query("SELECT e FROM Employee e WHERE e.department.deptId = :deptId")
	List<Employee> findByDeptId(@Param("deptId")Integer deptId);
	
}
