package com.douzone.lis_back.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {
    private String barcode;
    private String orderCode;
    private String stDate;
    private String endDate;
}