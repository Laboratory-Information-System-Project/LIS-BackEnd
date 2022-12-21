package com.douzone.lis_back.service.collectservice;


import com.douzone.lis_back.domain.collectdomain.CollectDomainDTO;
import com.douzone.lis_back.mapper.collectmapper.CollectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CollectServiceImpl implements CollectService{

    private final CollectMapper collectMapper;
    @Override
    public List<CollectDomainDTO> colletData(String barcode) {
        return collectMapper.colletData(barcode);
    }

    @Override
    public List<CollectDomainDTO> getCodeData(String barcode) {
        return collectMapper.getCodeData(barcode);
    }
}
