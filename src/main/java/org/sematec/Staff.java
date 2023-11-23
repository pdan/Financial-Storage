package org.sematec;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Staff {
    private String name;
    private int age;
    private String[] position; // Array
    private List<String> skills; // List
    private Map<String, BigDecimal> salary;

    public String getName() {
        return name;
    }

    public Staff setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Staff setAge(int age) {
        this.age = age;
        return this;
    }

    public String[] getPosition() {
        return position;
    }

    public Staff setPosition(String[] position) {
        this.position = position;
        return this;
    }

    public List<String> getSkills() {
        return skills;
    }

    public Staff setSkills(List<String> skills) {
        this.skills = skills;
        return this;
    }

    public Map<String, BigDecimal> getSalary() {
        return salary;
    }

    public Staff setSalary(Map<String, BigDecimal> salary) {
        this.salary = salary;
        return this;
    }
}
