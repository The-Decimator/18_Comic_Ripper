package com.Comic.ComicRipper;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class StatusController {

	@Autowired
	Terminal terminal;
	
	File dir = new File("./rclone-v1.53.0-linux-amd64");
	
	@MessageMapping("/send")
	@SendTo("/topic/messages")
	public Status send() throws IOException {
		if (terminal.process != null) {			
			if (terminal.flag) {
				Object[] line = terminal.Contents.toArray();
				terminal.Contents.clear();
				return new Status(line,"true");
			}
		}
		Object[] line = terminal.Contents.toArray();
		return new Status(line, "false");
	}
}
