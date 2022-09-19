package com.example.xmlparsingtest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "member_channel_table")
public class MemberChannelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long memberChannelId;

    // MemberChannelEntity(N)가 MemberEntity(1)을 참조함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    // MemberChannelEntity(N)가 ChannelEntity(1)을 참조함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id")
    private ChannelEntity channelEntity;
}
