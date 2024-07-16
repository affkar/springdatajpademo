package com.example.springbatchdemo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {

    private int id;
    private String name;
    private String address1;
    private String address2;
    private String city;

}
