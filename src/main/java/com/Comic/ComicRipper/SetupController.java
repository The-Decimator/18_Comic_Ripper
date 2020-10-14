package com.Comic.ComicRipper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SetupController {

	@Autowired
	Terminal terminal;

	@Autowired
	CommandLine commandLine;

	BufferedReader reader;
	BufferedWriter writer;
	File dir = new File("./rclone-v1.53.0-linux-amd64");
	@RequestMapping("/setUpRclone")
	public void root() throws IOException {
		
		String command = "";

		if (!dir.exists()) {
			terminal.StartProcess();
			reader = terminal.reader;
			writer = terminal.writer;

			command = "wget -q https://downloads.rclone.org/v1.53.0/rclone-v1.53.0-linux-amd64.zip";
			commandLine.commandLineFunction(reader, writer, command);
			System.out.println("Download completed");

			command = "unzip -q rclone-v1.53.0-linux-amd64.zip";
			writer.write("((" + command + ") && echo --EOF--) || echo --EOF--\n");
			writer.flush();
			System.out.println("Extraction completed");
			terminal.process = null;
		}

	}
}
