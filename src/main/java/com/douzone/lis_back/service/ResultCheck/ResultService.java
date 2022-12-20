package com.douzone.lis_back.service.ResultCheck;

import com.douzone.lis_back.domain.ResultCheck.ResultDTO;
import com.douzone.lis_back.domain.ResultCheck.ResultSearchDTO;

import java.util.List;

;

public interface ResultService {
    List<ResultDTO> allList();

    List<ResultDTO> getSearchResult(ResultSearchDTO ResultSearch);

    List<ResultDTO> searchNotDate(int text);

}