package com.example.xmlparsingtest;

import com.example.xmlparsingtest.dto.ChannelDTO;
import com.example.xmlparsingtest.dto.MemberDTO;
import com.example.xmlparsingtest.service.ChannelService;
import com.example.xmlparsingtest.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class XmlParsingTestApplicationTests {
    @Autowired
    private MemberService memberService;
    @Autowired
    private ChannelService channelService;

    @Test
    @DisplayName("회원저장")
    @Transactional
    @Rollback(value = false)
    void memberSaveTest() {
        MemberDTO memberDTO = new MemberDTO();
        memberService.save(memberDTO);
    }

    private static String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }

    @Test
    @DisplayName("DOM파싱테스트")
    @Transactional
    @Rollback(value = false)
    void domParsingTest() {
        try {
            // 파일 경로
            File channels = new File(Paths.get("").toAbsolutePath() + "/src/main/resources/static/data/channels.xml");
            // 파일 읽기
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(channels);
            doc.getDocumentElement().normalize();

            System.out.println("파일출력");

            List<ChannelDTO> channelDTOList = new ArrayList<>();
            // 읽어들인 파일 불러오기
            NodeList nodes = doc.getElementsByTagName("channel");
            for (int k = 0; k < nodes.getLength(); k++) {
                Node node = nodes.item(k);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    ChannelDTO channelDTO = new ChannelDTO();
                    Element element = (Element) node;
                    System.out.println("Channel Name: " + getValue("name", element));
                    channelDTO.setChannelName(getValue("name", element));
                    System.out.println("Channel Day: " + getValue("day", element));
                    channelDTO.setChannelDay(getValue("day", element));
                    System.out.println("Channel startTime: " + getValue("startTime", element));
                    channelDTO.setChannelStartTime(Integer.parseInt(getValue("startTime", element)));
                    System.out.println("Channel endTime: " + getValue("endTime", element));
                    channelDTO.setChannelEndTime(Integer.parseInt(getValue("endTime", element)));
                    System.out.println("Channel Num: " + getValue("num", element));
                    channelDTO.setChannelNum(Integer.parseInt(getValue("num", element)));
                    System.out.println("Channel Dsc: " + getValue("dsc", element));
                    channelDTO.setChannelDsc(getValue("dsc", element));
                    channelDTOList.add(channelDTO);
                    System.out.println(channelDTOList);
                }
            }
            for (ChannelDTO c : channelDTOList) {
                channelService.save(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    @DisplayName("SAX파싱테스트")
    @Transactional
    @Rollback(value = false)
    void saxParsingTest() {
        File channels = new File(Paths.get("").toAbsolutePath() + "/src/main/resources/static/data/channels.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            ChannelsSaxHandler handler = new ChannelsSaxHandler();
            parser.parse(channels, handler);

            List<ChannelDTO> list = handler.getStockList();

            for (ChannelDTO channelDTO : list) {
                System.out.println(channelDTO);
                channelService.save(channelDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
