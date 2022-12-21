package com.douzone.lis_back.domain.ResultCheck;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessagesRequestDTO {
    private String subject;
    private String to;
    private String content;
}
