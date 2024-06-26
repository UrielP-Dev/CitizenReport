package com.si.citizenreport.model;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    private String id;
    private String fullName;
    private String curp;
    private String phoneNumber;
    private String email;
    private String voucherAddress;
    private String idMex;
    private String password;


}
