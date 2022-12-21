package com.douzone.lis_back.mapper.collecting;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BarcodeMapper {
    String findBarcode();

    // barcode가 없거나 새로운 날짜 일때
    Integer insertNewBarcode(List<Object> prescribeCodeList, String today, String userId);

    // barcode가 있고 당일 일때
    // Integer insertBarcode(List<Long> prescribeCodeList, String today);

    void initBarcode();

    List<Map<String, Object>> findBarcodeByPrescribeCode(List<String> prescribeList);

    List<Map<String, Object>> findBarcodeByPrescribeCodeList(List<String> prescribeCodeList);

    Integer deleteBarcode(List<String> barcodeList, String userId);

    List<Map<Object, Object>> findAllByPrescribeCodeForKafka(List<String> prescribeCodeList);
}
