package com.douzone.lis_back.domain.patientdomain;

import lombok.Data;

@Data
public class PatientDTO {
    private int patientNo;
    private String patientName;
    private String patientPhoneNumber;
};