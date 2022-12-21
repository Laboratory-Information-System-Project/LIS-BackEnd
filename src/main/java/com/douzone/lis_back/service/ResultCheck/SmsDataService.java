package com.douzone.lis_back.service.ResultCheck;
import com.douzone.lis_back.domain.ResultCheck.SmsDataDTO;

import java.util.List;

public interface SmsDataService {
    List<SmsDataDTO> SmsAllList();
    public void insert(SmsDataDTO smsData);
    public void update(SmsDataDTO smsData);
    public void delete(int smsNo);
}
