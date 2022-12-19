package com.douzone.lis_back.domain.inspectiondomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InspectionAddDTO {
    private Long registerCode;
    private String inspectorId;
    private String barcode;
    private LocalDate registerDt;
    private Long prescribeCode;
    private String statusCode;
    private String cancelRegisterId;
    private LocalDate cancelRegisterDt;
}
