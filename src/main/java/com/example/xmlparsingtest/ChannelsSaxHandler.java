package com.example.xmlparsingtest;

import com.example.xmlparsingtest.dto.ChannelDTO;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ChannelsSaxHandler extends DefaultHandler {
    //파싱한 ChannelDTO 객체를 넣을 리스트
    private List<ChannelDTO> channelDTOList;
    //파싱한 ChannelDTO 객체
    private ChannelDTO channelDTO;
    //character 메소드에서 저장할 문자열 변수
    private String str;

    public ChannelsSaxHandler() {
        channelDTOList = new ArrayList<>();
    }

    public void startElement(String uri, String localName, String name, Attributes att) {
        //시작 태그를 만났을 때 발생하는 이벤트
        if(name.equals("channel")) {
            channelDTO = new ChannelDTO();
            channelDTOList.add(channelDTO);
        }
    }
    public void endElement(String uri, String localName, String name) {
        //끝 태그를 만났을 때,
        if(name.equals("name")) {
            channelDTO.setChannelName(str);
        }else if(name.equals("day")) {
            channelDTO.setChannelDay(str);
        }else if(name.equals("startTime")) {
            channelDTO.setChannelStartTime(Integer.parseInt(str));
        }else if(name.equals("endTime")) {
            channelDTO.setChannelEndTime(Integer.parseInt(str));
        }else if(name.equals("num")) {
            channelDTO.setChannelNum(Integer.parseInt(str));
        }else if(name.equals("dsc")) {
            channelDTO.setChannelDsc(str);
        }
    }
    public void characters(char[] ch, int start, int length) {
        //태그와 태그 사이의 내용을 처리
        str = new String(ch,start,length);
    }
    public List<ChannelDTO> getStockList(){
        return channelDTOList;
    }
    public void setChannelDTOList(List<ChannelDTO> channelDTOList) {
        this.channelDTOList=channelDTOList;
    }
}
