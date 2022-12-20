package com.douzone.lis_back.mapper.ResultCheck;

import com.douzone.lis_back.domain.ResultCheck.ResultDTO;
import com.douzone.lis_back.domain.ResultCheck.ResultSearchDTO;
import com.douzone.lis_back.domain.ResultCheck.SmsDataDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResultMapper {
    List<ResultDTO> selectAll();
    List<ResultDTO> getSearchResultMapper(ResultSearchDTO resultSearch);
    List<ResultDTO> searchNotDate(int text);

    List<SmsDataDTO> smsSelectAll();

    void insert(SmsDataDTO smsData);
    void update(SmsDataDTO smsData);
    void delete(@Param("smsNo") int smsNo);
}
