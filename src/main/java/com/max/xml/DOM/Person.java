package com.max.xml.DOM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by 1 on 09.07.2018.
 */

@AllArgsConstructor
public class Person {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private long telephone;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", telephone=" + telephone +
                '}';
    }
}
