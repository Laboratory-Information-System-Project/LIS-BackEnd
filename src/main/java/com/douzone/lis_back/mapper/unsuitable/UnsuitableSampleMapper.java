package com.douzone.lis_back.mapper.unsuitable;

import com.douzone.lis_back.domain.unsuitable.UnsuitableReasonDTO;
import com.douzone.lis_back.domain.unsuitable.UnsuitableSampleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UnsuitableSampleMapper {
    List<Map<String, Object>> findSampleByBarcode(@Param("barcode") Long barcode, @Param("authority") String authority);
    List<Map<String, Object>> findPrescribeByBarcode(@Param("barcode") Long barcode, @Param("authority") String authority);
    List<Map<String, Object>> findUnsuitableSample(List<Map<String, Object>> sample);
    List<Map<String, Object>> findUnsuitByBarcode(Long barcode);
    List<Map<String, Object>> findUsersByUsername(String userName);
    List<UnsuitableReasonDTO> findAllUnsuitableReason();
    void insertUnsuitableSampleInfo(Map<String, String> unsuitInfo);
    UnsuitableSampleDTO findUnsuitableSample(Map<String, String> unsuitInfo);
}
