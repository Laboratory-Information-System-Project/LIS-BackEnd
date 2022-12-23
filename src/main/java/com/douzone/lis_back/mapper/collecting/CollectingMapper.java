package com.douzone.lis_back.mapper.collecting;

import com.douzone.lis_back.domain.collectdomain.CollectDomainDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectingMapper {
    List<String> findCollectedPrescribeCode(List<Object> barcodeList);

    Integer updateCollectingData(List<Object> barcodeList, Object userId);

    List<String> findPrescribeCodeByBarcode(List<String> barcodeList);

    Integer deleteCollectingData(List<Object> barcodeList, Object userId);

    void updateCollectingForDelete(List<String> prescribeCodeList);
    void updateCancelInspection(Object prescribeCode, String status);

    List<CollectDomainDTO> colletData(String barcode);

    List<String> getCode (String barcode);
}
