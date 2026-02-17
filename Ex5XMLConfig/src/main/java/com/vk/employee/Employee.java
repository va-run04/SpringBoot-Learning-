package com.vk.employee;

import com.vk.department.Department;

public class Employee {

    private int empId;
    private String name;
    private double salary;
    private Department department;  // HAS-A relationship

    // Constructor (used for constructor injection)
    public Employee(int empId, String name, double salary) {
        this.empId = empId;
        this.name = name;
        this.salary = salary;
    }

    // No-arg constructor (needed for setter injection)
    public Employee() {
    	
    }

    // Setters (needed for setter injection and for department)
    public void setEmpId(int empId) { 
    	
    	this.empId = empId; 
    	
    	}
    public void setName(String name) {
    	
    	this.name = name;
    	
    	}
    public void setSalary(double salary) { 
    	
    	this.salary = salary; 
    	
    	}
    public void setDepartment(Department department) { 
    	
    	this.department = department;
    	
    	}

    @Override
    public String toString() {
        return "Employee{empId=" + empId
                + ", name='" + name + "'"
                + ", salary=" + salary
                + ", department=" + department + "}";
    }
}