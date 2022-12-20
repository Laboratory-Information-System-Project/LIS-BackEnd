package com.douzone.lis_back.service.ResultCheck;


import com.douzone.lis_back.domain.ResultCheck.MessagesRequestDTO;
import com.douzone.lis_back.domain.ResultCheck.SendSmsResponseDTO;
import com.douzone.lis_back.domain.ResultCheck.SmsRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Component
@Slf4j
@ConfigurationPropertiesScan
public class SmsService {

    private static String naverServiceId;
    @Value("${spring.api.naver-sms-service-id}")
    public void setNaverServiceId(String path) {
        naverServiceId = path;
    }

    private static String naverAccessKey;
    @Value("${spring.api.naver-sms-access-key}")
    public void setNaverAccessKey(String path) {
        naverAccessKey = path;
    }

    private static String naverSecretKey;
    @Value("${spring.api.naver-sms-secret-key}")
    public void setNaverSecretKey(String path) {
        naverSecretKey = path;
    }

    public static SendSmsResponseDTO sendSms(String subject, String recipientPhoneNumber, String content) throws ParseException, JsonProcessingException, UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException {
        Long time = System.currentTimeMillis();
        List<MessagesRequestDTO> messages = new ArrayList<>();

        System.out.println("========= key값 ========");
        System.out.println(naverServiceId);
        System.out.println(naverSecretKey);
        System.out.println(naverAccessKey);
        System.out.println("======================");

        // 보내는 사람에게 내용을 보냄.
        messages.add(new MessagesRequestDTO(subject, recipientPhoneNumber, content)); // content부분이 내용임

        // 전체 json에 대해 메시지를 만든다.
        SmsRequestDTO smsRequestDto = new SmsRequestDTO("더존 병원에서 알려드립니다.", "SMS", "COMM", "82", "01046766654", "MangoLtd", messages);

        // 쌓아온 바디를 json 형태로 변환시켜준다.
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(smsRequestDto);

        // 헤더에서 여러 설정값들을 잡아준다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", naverAccessKey);
        headers.set("x-ncp-apigw-signature-v2", makeSignature(time));

        // 위에서 조립한 jsonBody와 헤더를 조립한다.
        HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);
        System.out.println("아래는 getBody 호출");
        System.out.println(body.getBody()); // 합쳐진 body 호출
        System.out.println("-------------------------------");
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus statusCode = response.getStatusCode();
                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
            }
        });

        SendSmsResponseDTO sendSmsResponseDTO = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+naverServiceId+"/messages"), body, SendSmsResponseDTO.class);
        System.out.println("아래 getStatusCode()");
        System.out.println(sendSmsResponseDTO.getStatusCode());
        System.out.println("----------------------------");
        return sendSmsResponseDTO;
    }

    public static String makeSignature(Long time) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        String space = " ";                    // one space
        String newLine = "\n";                    // new line
        String method = "POST";                    // method
        String url = "/sms/v2/services/" + naverServiceId + "/messages";    // url (include query string)
        String timestamp = time.toString();            // current timestamp (epoch)
        String accessKey = naverAccessKey;            // access key id (from portal or Sub Account)
        String secretKey = naverSecretKey;

        System.out.println("시그니쳐 실행");

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        System.out.println("-------------message----------------");
        System.out.println(message);
        System.out.println("====================================");

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);
        return encodeBase64String;
    }
}
