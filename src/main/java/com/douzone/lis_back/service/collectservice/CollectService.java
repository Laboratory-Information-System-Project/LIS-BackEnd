package com.douzone.lis_back.service.collectservice;


import com.douzone.lis_back.domain.collectdomain.CollectDomainDTO;

import java.util.List;

public interface CollectService {
    List<CollectDomainDTO> colletData(String barcode);
    List<CollectDomainDTO> getCodeData(String barcode);
}
