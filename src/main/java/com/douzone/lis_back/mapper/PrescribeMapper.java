package com.douzone.lis_back.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PrescribeMapper {
    List<Map<String, Object>> findPrescribeByVisitNo(Long visitNo);

    Integer update(List<Object> prescribeCodeList, String status);

    List<String> findStatus(List<Object> prescribeCodeList, String status);
}
