package com.si.citizenreport.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String id;
    private String fullName;
    private String curp;
    private String phoneNumber;
    private String email;
    private String voucherAddress;
    private String idMex;
    private String password;

    public User(String fullName, String curp, String phoneNumber, String email, String voucherAddress, String idMex, String password) {
        this.fullName = fullName;
        this.curp = curp;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.voucherAddress = voucherAddress;
        this.idMex = idMex;
        this.password = password;
    }

}
