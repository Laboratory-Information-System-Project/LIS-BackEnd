package com.douzone.lis_back.controller.ResultCheck;

import com.douzone.lis_back.domain.ResultCheck.SmsDataDTO;
import com.douzone.lis_back.service.ResultCheck.SmsDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/smsData")
@RequiredArgsConstructor
@Slf4j
public class SmsDataController {
    private final SmsDataService service;

    @GetMapping("/all")
    public List<SmsDataDTO> smsDataList(){
        return service.SmsAllList();
    }

    @PostMapping("/add")
    void insertSmsData(@RequestBody SmsDataDTO smsData) {
        service.insert(smsData);
        System.out.println("유저 DB 저장 성공");
    }

    @PutMapping("/{smsNo}")
    public void update(@PathVariable int smsNo, @RequestBody SmsDataDTO smsData) {
        SmsDataDTO update = smsData;
        System.out.println("업데이트유저 => " + update);

        update.setSmsTitle(smsData.getSmsTitle());
        update.setSmsContent(smsData.getSmsContent());
        service.update(smsData);
    }

    @DeleteMapping("/{smsNo}")
    public void delete(@PathVariable String smsNo) {
        service.delete(Integer.parseInt(smsNo));
    }
}

