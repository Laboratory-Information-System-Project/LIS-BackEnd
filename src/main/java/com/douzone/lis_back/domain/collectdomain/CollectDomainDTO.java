package com.douzone.lis_back.domain.collectdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectDomainDTO {
    private String barcode;
    private Date collectingDt;
    private String collectorId;
    private Long prescribeCode;
    private String inspectionCode;
    private String orderCode;
    private String vesselCode;
    private String statusCode;
    private String sampleCode;
    private Date cancelBarcodeDt;
    private Date cancelCollectingDt;
    private Long patientNo;
    private String unsuitableStatusCode;
    private String statusName;
    private String userName;
}
