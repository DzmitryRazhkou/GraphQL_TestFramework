package com.qa.lombok;

public class LombokTest{
    public static void main(String[] args) {

        Person person = new Person("Tom", 1990, true, "10-20-2021");
        System.out.println(person.getDob() + " " + person.getYear());

        User u1 = User.builder()
                .name("Tim")
                .age(32)
                .dob("02-05-1990")
                .build();

        System.out.println(u1.getAge()+ " " + u1.getName());
    }


}
