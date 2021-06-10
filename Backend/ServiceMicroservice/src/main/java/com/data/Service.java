package com.data;

import lombok.Data;

import java.util.Date;

@Data
public class Service {
    private int id;
    private String name;
    private int employeeId;
    private Date date;
    private double longitude;
    private double latitude;
    private String address;
}
