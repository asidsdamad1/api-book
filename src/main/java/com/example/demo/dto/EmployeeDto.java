package com.example.demo.dto;

import com.example.demo.domain.Employee;

public class EmployeeDto {

    private String code;
    private String name;
    private String email;
    private String phone;
    private int age;

    public EmployeeDto(Employee entity) {
        if (entity != null) {
            this.setCode(entity.getCode());
            this.setName(entity.getName());
            this.setEmail(entity.getEmail());
            this.setPhone(entity.getPhone());
            this.setAge(entity.getAge());
        }

    }

    public EmployeeDto() {
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
