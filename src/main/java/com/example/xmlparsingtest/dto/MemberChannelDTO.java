package com.example.xmlparsingtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberChannelDTO {
    private Long memberChannelId;
    private String orderDepartureAirport;
    private String orderDepartureDate;
    private String orderDepartureNumber;
    private Long memberId;
    private Long orderId;
}
