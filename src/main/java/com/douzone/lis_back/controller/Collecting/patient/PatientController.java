package com.douzone.lis_back.controller.Collecting.patient;

import com.douzone.lis_back.service.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data-service")
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/patient/info/{patientInfo}/{searchCon}")
    public List<HashMap<String, Object>> getPatientInfo(@PathVariable String patientInfo,
                                                        @PathVariable String searchCon){
        return patientService.getPatientInfoByPatientNo(patientInfo, searchCon);
    }

    @GetMapping("/visit/{patientNo}/{visitStatus}")
    public List<HashMap<String,Object>> getVisitInfo(@PathVariable String patientNo,
                                                     @PathVariable String visitStatus){
        return patientService.getVisitDataByPatientNo(patientNo, visitStatus);
    }
}
