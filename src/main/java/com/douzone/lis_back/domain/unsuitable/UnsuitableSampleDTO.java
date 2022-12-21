package com.douzone.lis_back.domain.unsuitable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnsuitableSampleDTO {
    private String barcode;
    private String unsuitableReasonCode;
    private String unsuitableReasonDetail;
    private String notifiedUserId;
    private String notificatorId;
}
