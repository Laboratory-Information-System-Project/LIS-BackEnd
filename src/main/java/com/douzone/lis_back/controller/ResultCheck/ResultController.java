package com.douzone.lis_back.controller.ResultCheck;

import com.douzone.lis_back.domain.ResultCheck.ResultDTO;
import com.douzone.lis_back.domain.ResultCheck.ResultSearchDTO;
import com.douzone.lis_back.service.ResultCheck.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
@Slf4j
public class ResultController {

    private final ResultService service;

    @GetMapping("/all")
    public List<ResultDTO> resultList(){
        return service.allList();
    }

    @GetMapping("/search")
    public List<ResultDTO> getResult(ResultSearchDTO resultSearch){
        return service.getSearchResult(resultSearch);
    }

    @GetMapping("/{text}")
    public List<ResultDTO> searchNotDate(@PathVariable int text){
        return service.searchNotDate(text);
    }

}

