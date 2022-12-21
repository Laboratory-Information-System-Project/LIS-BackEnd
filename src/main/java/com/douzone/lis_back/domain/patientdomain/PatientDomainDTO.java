package com.douzone.lis_back.domain.patientdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDomainDTO {
    private Long patientNo;
    private String patientName;
    private int patientAge;
    private String patientBloodType;
    private int patientHeight;
    private int patientWeight;
    private String patientAddress;
    private String patientPhoneNumber;
    private String patientResidentNumber;
    private String patientGender;
    private String statusCode;
    private Date cancelBarcodeDt;
    private Date cancelCollectingDt;
}
