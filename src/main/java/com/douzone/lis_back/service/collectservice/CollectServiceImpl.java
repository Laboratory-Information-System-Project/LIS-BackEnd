package com.douzone.lis_back.service.collectservice;


import com.douzone.lis_back.domain.collectdomain.CollectDomainDTO;
import com.douzone.lis_back.mapper.collectmapper.CollectMapper;
import com.douzone.lis_back.mapper.collecting.CollectingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectServiceImpl implements CollectService{

    private final CollectMapper collectMapper;
    private final CollectingMapper collectingMapper;
    @Override
    public List<CollectDomainDTO> colletData(String barcode) {
        return collectingMapper.colletData(barcode);
    }

    @Override
    public List<CollectDomainDTO> getCodeData(String barcode) {
        return collectMapper.getCodeData(barcode);
    }
}
