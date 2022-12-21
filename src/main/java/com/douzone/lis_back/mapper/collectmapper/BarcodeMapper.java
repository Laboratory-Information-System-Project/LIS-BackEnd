package com.douzone.lis_back.mapper.collectmapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BarcodeMapper {
    List<Map<String, Object>> findBarcodeByPrescribeCodeList(List<String> prescribeCodeList);
}
