package com.douzone.lis_back.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InspectionTypeDTO {
    private String inspectionCode;
    private String orderCode;
    private String inspectionName;
    private String unit;
    private Double baseline;
}
