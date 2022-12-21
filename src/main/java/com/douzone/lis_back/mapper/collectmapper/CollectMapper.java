package com.douzone.lis_back.mapper.collectmapper;


import com.douzone.lis_back.domain.collectdomain.CollectDomainDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectMapper {
    List<CollectDomainDTO> colletData(String barcode);
    List<CollectDomainDTO> getCodeData(String barcode);
}
