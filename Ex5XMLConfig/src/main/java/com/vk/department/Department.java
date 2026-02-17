package com.vk.department;

public class Department {

    private int deptId;
    private String deptName;

    // Constructor (used for constructor injection)
    public Department(int deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    // No-arg constructor (needed for setter injection)
    public Department() {
    	
    }

    // Setters (needed for setter injection)
    public void setDeptId(int deptId) { 
    	this.deptId = deptId; 
    	}
    public void setDeptName(String deptName) { 
    	this.deptName = deptName; 
    	}

    @Override
    public String toString() {
        return "Department{deptId=" + deptId + ", deptName='" + deptName + "'}";
    }
}