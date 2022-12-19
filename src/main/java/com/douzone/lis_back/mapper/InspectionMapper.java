package com.douzone.lis_back.mapper;

import com.douzone.lis_back.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface InspectionMapper {

    List<RegisterDTO> getUnregisteredMapper();

    List<RegisterDTO> getSearchRegisterMapper(SearchDTO search);

    List<InspectionTypeDTO> getSearchInspectionTypeMapper(String orderCode);

    List<ConclusionDTO> getSelectConclusionMapper(SearchDTO search);

   String getPrescribeCode(Map<String, Object> barcode);

   List<UnsuitableStatusDTO> getUnsuitableStatus();

    void insertConclusionBatchMapper(List<ConclusionDTO> conclusion);

    void updateConclusionBatchMapper(List<ConclusionDTO> conclusion);

    void updatePrescribeStatusMapper(Long prescribeCode);


}
