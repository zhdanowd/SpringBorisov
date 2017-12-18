package com.zhdanov.reflection;

public class Person {
    private String name;
    private String age;

    public Person() {
        name = "dima";
        age = "23";
    }

    @RunThisMethod
    public void printDetails(){
        System.out.println(name + " is " + age + " years old");
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(final String age) {
        this.age = age;
    }
}
