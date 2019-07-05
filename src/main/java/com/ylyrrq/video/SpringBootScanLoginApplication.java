package com.ylyrrq.video;

import com.ylyrrq.video.pojo.SocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
@Controller
@EnableScheduling
@SpringBootApplication
@EnableCaching
public class SpringBootScanLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootScanLoginApplication.class, args);
	}

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@MessageMapping("/send")
	@SendTo("/topic/send")
	public SocketMessage send(SocketMessage message,String oauth,String img2) throws Exception {
		System.out.println(img2);
		System.out.println(oauth);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int index1=oauth.indexOf(":");
		int index2=oauth.indexOf(",");
		String msg=oauth.substring((index1+2),index2-1);
		oauth=oauth.substring(index2+1);
		index1=oauth.indexOf(":");
		index2=oauth.indexOf(",");
		oauth=oauth.substring(index2+1);
		index1=oauth.indexOf(":");
		index2=oauth.indexOf("}");
		String img=oauth.substring((index1+2),index2-1);
		System.out.println(img);
		System.out.println(msg);
		message.img=img;
		message.date = df.format(new Date());
		return message;
	}

	@Scheduled(fixedRate = 1000)
	@SendTo("/topic/callback")
	public Object callback() throws Exception {
		// 发现消息
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		messagingTemplate.convertAndSend("/topic/callback", df.format(new Date()));
		return "callback";
	}

}
