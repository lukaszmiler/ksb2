package com.example.ksb2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {
    private int id;
    private String mark;
    private String model;
    private String color;
}
