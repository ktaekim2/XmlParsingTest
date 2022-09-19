package com.example.xmlparsingtest.controller;

import com.example.xmlparsingtest.dto.ChannelDTO;
import com.example.xmlparsingtest.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/channel")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    @GetMapping("/save")
    public String save() {
        try {
            // 파일 경로
            File channels = new File(Paths.get("").toAbsolutePath()+"/src/main/resources/static/data/channels.xml");
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
            for (ChannelDTO c: channelDTOList) {
            channelService.save(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }
}
