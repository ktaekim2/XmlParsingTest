package com.example.xmlparsingtest.service;

import com.example.xmlparsingtest.dto.ChannelDTO;
import com.example.xmlparsingtest.entity.ChannelEntity;
import com.example.xmlparsingtest.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelRepository channelRepository;

    public void save(ChannelDTO channelDTO) {
        channelRepository.save(ChannelEntity.toEntity(channelDTO));
    }
}
