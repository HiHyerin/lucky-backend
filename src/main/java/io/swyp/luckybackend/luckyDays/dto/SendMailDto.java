package io.swyp.luckybackend.luckyDays.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMailDto {
    private String to;
    private String nickname;
//    private String from;
    private String subject;
    private String content;
}
