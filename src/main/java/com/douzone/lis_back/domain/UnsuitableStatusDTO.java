package com.douzone.lis_back.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnsuitableStatusDTO {
    private String barcode;
    private Long prescribeCode;
    private String unsuitableStatusCode;
    private String unsuitableReasonCode;
    private String unsuitableReasonName;
    private String unsuitableReasonText;
    private LocalDateTime unsuitableUpdateDt;

}
