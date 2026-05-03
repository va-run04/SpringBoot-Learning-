package com.vk.model;

public class Employee {

    private Integer id;
    private String name;
    private String city;

    public Employee(Integer id, String name, String city) {
        this.id   = id;
        this.name = name;
        this.city = city;
    }

    public Integer getId()     { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName()    { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity()    { return city; }
    public void setCity(String city) { this.city = city; }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", city=" + city + "]";
    }
}