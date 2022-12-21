package com.douzone.lis_back.service.barcode;

import com.douzone.lis_back.mapper.collecting.BarcodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BarcodeServiceImpl implements BarcodeService {
    private final BarcodeMapper barcodeMapper;

    private static final String PRESCRIBE_CODE = "prescribe_code";

    @Override
    public String createBarcode(Map<String, List<Object>> prescribeCodeList) {
        // 가장 최근 생성된 바코드를 찾아서 시퀀스와 날짜를 확인하고 새로운 바코드를 생성
        String barcode = barcodeMapper.findBarcode();
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));

        // List<String> statusCode = barcodeMapper.checkStatus(prescribeCodeList.get("prescribeCodeList"));

        // for (int i = 0; i < statusCode.size(); i++) {
        //     if(!"X".equals(statusCode.get(i))){
        //         return "failed create barcode!";
        //     }
        //
        // }

        // barcode가 없을때

        if (Objects.isNull(barcode) || Objects.equals(false, today.matches(barcode.substring(0,6)))) {
            // 시퀀스를 초기화 하고 바코드를 새로 생성
            barcodeMapper.initBarcode();
            return insertNewBarcode(prescribeCodeList.get("prescribeCodeList"), today, prescribeCodeList.get("userId").get(0));
        }

        // barcode가 있고 당일 일때
        return insertNewBarcode(prescribeCodeList.get("prescribeCodeList"), today, prescribeCodeList.get("userId").get(0));

    }

    @Override
    public List<Map<String, Object>> getBarcodeList(List<String> prescribeCodeList) {

        return barcodeMapper.findBarcodeByPrescribeCode(prescribeCodeList);
    }

    @Override
    public String removeBarcode(Map<String, List<String>> barcodeList) {
        Integer result = barcodeMapper.deleteBarcode(barcodeList.get("prescribeCodeList"), barcodeList.get("userId").get(0));

        if(result == barcodeList.get("prescribeCodeList").size()){
            return "선택하신 바코드 발급이 취소되었습니다";
        }
        return "바코드 발급 취소가 실패하였습니다.";
    }

    @Override
    public List<Map<Object, Object>> getAll(List<String> prescribeCodeList) {
        return barcodeMapper.findAllByPrescribeCodeForKafka(prescribeCodeList);
    }

    @Override
    public List<Map<String, Object>> getBarcodeByPrescribeCodeList(List<String> prescribeCodeList) {
        List<Map<String, Object>> barcodeList = barcodeMapper.findBarcodeByPrescribeCodeList(prescribeCodeList);
        int length = barcodeList.size();
        Map <String,Object> result = barcodeList.get(0);

        List<Map<String, Object>> barcodeInfoForReturn = new ArrayList<>();
        barcodeInfoForReturn.add(result);

        for (int i = 0; i < length - 1; i++) {
            Map<String, Object> current = barcodeList.get(i);
            Map<String, Object> next = barcodeList.get(i+1);

            String currentBarcode = (String) current.get("barcode");
            String nextBarcode = (String) next.get("barcode");

            if(!currentBarcode.equals(nextBarcode)){
                barcodeInfoForReturn.add(next);
            }else {
                String currentPrescribeCode = result.get(PRESCRIBE_CODE).toString();
                currentPrescribeCode = currentPrescribeCode.concat( ","+ next.get(PRESCRIBE_CODE).toString());
                result.put(PRESCRIBE_CODE, currentPrescribeCode);
            }


        }

        return barcodeInfoForReturn;
    }

    private String insertNewBarcode(List<Object> prescribeCodeList, String today, Object userId) {
        Integer result;
        try {
            result = barcodeMapper.insertNewBarcode(prescribeCodeList, today, userId.toString());
        }catch(Exception e){
            return "failed create barcode!";
        }

        if (result==1) {
            return "create barcode successfully!";
        } else {
            return "failed create barcode!";
        }
    }
}
