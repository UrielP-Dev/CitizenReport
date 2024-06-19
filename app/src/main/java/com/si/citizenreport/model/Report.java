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
    private String photo; // Asegúrate de que es un string si el servidor envía una cadena base64
    private String description;
    private String location;
    private Date date;
    private String time;
    private String plate;
    private String status;
    private String userId;
    private String managerID;
}