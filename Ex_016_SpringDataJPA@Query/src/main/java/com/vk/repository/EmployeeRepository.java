package com.vk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpqlQueryBuilder;
import org.springframework.data.repository.query.Param;

import com.vk.model.Employee;

import jakarta.transaction.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	//Here we are creating custom Queries
	//find by single field
	List<Employee> findByCity(String city);
	List<Employee> findByName(String name);
	
	// find by multiple fields
	List<Employee> findByCityAndName(String name, String city);
	List<Employee> findByCityOrName(String name, String city);
	

	//Check existence
	boolean existsByName(String name);
	
	//count
	long countByCity(String city);
	
	//delete (It requires @Transactional because here data is being modified and of something 
	// goes wrong and error occurs then it rolls back to original state
	// Transactions are not applied to read operation or fetch operations
	@Transactional
	void deleteByCity(String city);
	
	
	//----- @Query Methods ----
	
	//JPQL - Fetch ALL employees from a city
	
	@Query("SELECT e FROM Employee e WHERE e.city = :city")
	List<Employee> getEmployeesByCity(@Param("city") String city);
	
	
	@Query("SELECT e.name FROM Employee e")
	List<String> getAllEmployeeNames();
	
	@Query("SELECT e FROM Employee e WHERE e.name LIKE %:keyword%")
	List<Employee> searchByName(@Param("keyword") String keyword);
	
	// Native SQL same as above but uses Column/Table names
	@Query(value = "SELECT * FROM employee WHERE city = :city",nativeQuery = true)
	List<Employee> getEmployessByCityNative(@Param("city") String city);
	
	
	// JPQL — custom UPDATE using @Modifying
    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.city = :city WHERE e.id = :id")
    int updateEmployeeCity(@Param("id") Long id, @Param("city") String city);

	
	
	//JPQL  - custom delete using @Modifying
	@Modifying
	@Transactional
	@Query("DELETE FROM Employee e WHERE e.city = :city")
	int deleteEmployeesByCity(@Param("city") String city);
	
	
	
	
	
	

	
}
