package com.example.xmlparsingtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelDTO {
    private Long channelId;
    private String channelName;
    private String channelDay;
    private int channelStartTime;
    private int channelEndTime;
    private int channelNum;
    private String channelDsc;
}