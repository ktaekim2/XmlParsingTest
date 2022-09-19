package com.example.xmlparsingtest.entity;
import com.example.xmlparsingtest.dto.ChannelDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "channel_table")
public class ChannelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long channelId;

    @Column(length = 20, nullable = false)
    private String channelName;

    @Column(length = 10, nullable = false)
    private String channelDay;

    @Column(nullable = false)
    private int channelStartTime;

    @Column(nullable = false)
    private int channelEndTime;

    @Column(nullable = false)
    private int channelNum;

    @Column(length = 50, nullable = false)
    private String channelDsc;

    // ChannelEntity(1)이 MemberChannelEntity(n)한테 참조당함
    @OneToMany(mappedBy = "channelEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MemberChannelEntity> memberChannelEntityList = new ArrayList<>();

    public static ChannelEntity toEntity(ChannelDTO channelDTO) {
        ChannelEntity channelEntity = new ChannelEntity();
        channelEntity.setChannelDay(channelDTO.getChannelDay());
        channelEntity.setChannelName(channelDTO.getChannelName());
        channelEntity.setChannelDsc(channelDTO.getChannelDsc());
        channelEntity.setChannelStartTime(channelDTO.getChannelStartTime());
        channelEntity.setChannelEndTime(channelDTO.getChannelEndTime());
        channelEntity.setChannelNum(channelDTO.getChannelNum());
        return channelEntity;
    }
}