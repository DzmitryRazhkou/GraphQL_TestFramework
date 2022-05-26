package com.qa.lombok;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Person {

    private String name;
    private int year;
    private boolean isActive;
    private String dob;
}
