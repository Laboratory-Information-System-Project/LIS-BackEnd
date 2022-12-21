package com.douzone.lis_back.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface InspectionAddMapper {
       void InspectionAdd(List<Object> barcodeList);
       void updateMapper(HashMap<String,String>updateData);

       Integer updatePrescribeCode(List<HashMap<String, Object>> prescribeList);

       Integer updatePrescribeCodeData(List<HashMap<String, Object>> prescribeList);

}
