package com.douzone.lis_back.domain.ResultCheck;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultSearchDTO {
    private int patientNo;
    private String startDate;
    private String endDate;
    private String radioDate;
    private String orderSelect;
}
