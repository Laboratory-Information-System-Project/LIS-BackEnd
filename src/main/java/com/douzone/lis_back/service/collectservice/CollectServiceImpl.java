package com.douzone.lis_back.service.collectservice;


import com.douzone.lis_back.domain.collectdomain.CollectDomainDTO;
import com.douzone.lis_back.mapper.collecting.CollectingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectServiceImpl implements CollectService{

    private final CollectingMapper collectMapper;
    @Override
    public List<CollectDomainDTO> colletData(String barcode) {
        return collectMapper.colletData(barcode);
    }
}
