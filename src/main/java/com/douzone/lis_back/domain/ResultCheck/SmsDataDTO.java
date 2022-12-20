package com.douzone.lis_back.domain.ResultCheck;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class SmsDataDTO {
    private int SmsNo;
    private String SmsTitle;
    private String SmsContent;

    @Builder
    public SmsDataDTO(String SmsTitle, String SmsContent) {
        this.SmsTitle = SmsTitle;
        this.SmsContent = SmsContent;
    }

}