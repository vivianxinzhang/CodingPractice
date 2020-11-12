package com.company.Practice;

public class People {
    String name;
    People(String name) {
        this.name = name;
    }
    class Bike {
        String owner = name;
        People p = People.this;
        void printName() {
            System.out.println(name == p.name);
        }
    }
}
