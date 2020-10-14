package com.Comic.ComicRipper;

import java.io.IOException;
import java.io.Writer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StopCopyingController {

	@RequestMapping("/stopCopying")
	public void stopCopying(Writer responseWriter) throws IOException {
		System.exit(0);
	}
}
