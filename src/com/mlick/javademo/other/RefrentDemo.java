package com.mlick.javademo.other;

public class RefrentDemo {


    public static void main(String[] args) {

        Person p = new Person("张三");

        change(p);

        System.out.println(p.name);
    }

    private static void change(Person p) {
        Person person1 = new Person("李四");
        p = person1;
    }


    public static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }

    }
}
