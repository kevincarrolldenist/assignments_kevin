package com.demo.day4;

public class employee{
    private String name;
    private int age;
    private String department;

    public employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                '}';
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
