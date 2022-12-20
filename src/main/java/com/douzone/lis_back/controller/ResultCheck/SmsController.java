package com.douzone.lis_back.controller.ResultCheck;

import com.douzone.lis_back.domain.ResultCheck.MessagesRequestDTO;
import com.douzone.lis_back.domain.ResultCheck.SendSmsResponseDTO;
import com.douzone.lis_back.service.ResultCheck.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

@RestController
@RequestMapping("/sendSms")
@RequiredArgsConstructor
@Controller
public class SmsController {

    private SmsService service;

    @GetMapping("/")
    public String getSmsPage() {
        return "sendSms";
    };

    @PostMapping(value = "/post", produces="application/json", consumes = "application/json")
    public ResponseEntity<SendSmsResponseDTO> test(@RequestBody MessagesRequestDTO messagesRequestDTO) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
        SendSmsResponseDTO data = service.sendSms(messagesRequestDTO.getSubject(), messagesRequestDTO.getTo(), messagesRequestDTO.getContent());
        return ResponseEntity.ok().body(data);
    };

}

