package com.douzone.lis_back.service;

import com.douzone.lis_back.domain.*;

import java.util.List;

public interface InspectionService {

    List<RegisterDTO> getUnregistered();

    List<RegisterDTO> getSearchRegister(SearchDTO search);


    List<InspectionTypeDTO> getSearchInspectionType(String orderCode);

    List<ConclusionDTO> getSelectConclusion(SearchDTO search);

    List<UnsuitableStatusDTO> getUnsuitableStatus();

}
