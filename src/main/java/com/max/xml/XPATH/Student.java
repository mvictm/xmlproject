package com.max.xml.XPATH;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by 1 on 10.07.2018.
 */

@AllArgsConstructor
public class Student {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private long number;

    @Getter
    @Setter
    private String gender;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", gender='" + gender + '\'' +
                '}';
    }
}
