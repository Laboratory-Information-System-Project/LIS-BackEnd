package com.douzone.lis_back.domain.unsuitable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnsuitableReasonDTO {
    private String unsuitableReasonCode;
    private String unsuitableReasonName;
    private String unsuitableStatusCode;
    private String unsuitableStatusName;
}
