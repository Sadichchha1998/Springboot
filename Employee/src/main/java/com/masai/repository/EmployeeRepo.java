package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masai.model.Address;
import com.masai.model.Employee;
import com.masai.model.EmployeeDTO;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	//Abstract method
 Employee findByEmail(String email);
 Employee findByEmailAndPassword(String email,String password);
 List<Employee> findByAddress(Address address);
 List<Employee> findByAgeBetween(Integer start_age, Integer end_age);
 //List<EmployeeDTO> findByEmployeeNameAddressAge(EmployeeDTO employeedto);
 //List<EmployeeDTO> findByNameAndAddressAndAge(String employeeName, String address, Integer age);

 
 //@Query("SELECT e FROM Employee e WHERE e.name = :name AND e.address.pincode = :pincode AND e.address.city = :city AND e.address.state = :state AND e.age = :age")
 //List<Employee> findByEmployeeNameAndAddressDetails(@Param("name") String name, @Param("pincode") String pincode,@Param("city") String city, @Param("state") String state, @Param("age") Integer age);

 
	@Query("select new com.masai.model.EmployeeDTO(e.employeeName, e.address, e.age) from Employee e")
	List<EmployeeDTO> findEmployeeNameAddressAndAge();
}
