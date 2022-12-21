package com.douzone.lis_back.service.patient;

import com.douzone.lis_back.domain.PatientDTO;
import com.douzone.lis_back.domain.patientdomain.PatientDomainDTO;

import java.util.HashMap;
import java.util.List;

public interface PatientService {
    List<HashMap<String, Object>> getPatientInfoByPatientNo(String patientNo, String visitStatus, String searchCon);

    List<PatientDomainDTO>patientData(String barcode);

    List<PatientDTO> patientList();

    List<HashMap<String, Object>> getVisitDataByPatientNo(String patientNo);
}
