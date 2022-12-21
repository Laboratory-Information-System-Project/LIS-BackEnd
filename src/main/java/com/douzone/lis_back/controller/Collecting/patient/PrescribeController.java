package com.douzone.lis_back.controller.Collecting.patient;

import com.douzone.lis_back.service.prescribe.PrescribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data-service/prescribe")
public class PrescribeController {

    private final PrescribeService prescribeService;

    @GetMapping("/{visitNo}")
    public List<Map<String, Object>> getPrescribe(@PathVariable Long visitNo){
        return prescribeService.getPrescribeInfoByVisitNo(visitNo);
    }
}
