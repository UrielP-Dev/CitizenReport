package com.si.citizenreport.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    private String id;
    private byte[] photo;
    private String description;
    private String location;
    private Date date;
    private String time;
    private String plate;
    private String status;
    private String userId;
    private String managerID;
}