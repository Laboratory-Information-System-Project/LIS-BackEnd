package com.douzone.lis_back.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConclusionDTO {
    private Long resultNo;
    private Long registerCode;
    private String barcode;
    private String inspectionCode;
    private String figures;
    private LocalDateTime inspectionDt;
    private LocalDateTime reportedDt;
    private String note;
    private String sampleNote;
    private String orderCode;
}
