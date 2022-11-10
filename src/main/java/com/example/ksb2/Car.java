package com.example.ksb2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private int id;
    private String mark;
    private String model;
    private String color;

    public static boolean equals(Car a, Car b){
        return a.getId() == b.getId() && a.getMark().equalsIgnoreCase(b.getMark()) && a.getModel().equalsIgnoreCase(b.getModel()) && a.getColor().equalsIgnoreCase(b.getColor());
    }
}


