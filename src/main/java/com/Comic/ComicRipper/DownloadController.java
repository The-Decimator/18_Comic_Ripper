package com.Comic.ComicRipper;

import java.io.IOException;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DownloadController {

	Thread t1;
	@Autowired
	PreventingIdling preventingIdling;

	Timer timer = new Timer();

	@Autowired
	ComicRip comicRip;

	@RequestMapping("/Download")
	public void Download(@RequestParam("links") MultipartFile links, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		t1 = new Thread() {
			public void run() {
				preventingIdling.Idling(timer);
			}
		};
		t1.start();
		
		comicRip.rip(links);
		
		timer.cancel();
	}
}
