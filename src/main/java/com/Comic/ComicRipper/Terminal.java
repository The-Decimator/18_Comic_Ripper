package com.Comic.ComicRipper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class Terminal {
	
	BufferedReader reader;
	BufferedWriter writer;
	Process process;
	ProcessBuilder builder = new ProcessBuilder();
	ArrayList<String> Contents = new ArrayList<String>();
	Boolean flag = false;

	public void StartProcess() throws IOException {
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

		if (isWindows) {
			builder.command("cmd.exe");
		} else {
			builder.command("/bin/bash");
		}
		
		builder.redirectErrorStream(true);
		process = builder.start();
		OutputStream stdin = process.getOutputStream();
		InputStream stdout = process.getInputStream();

		reader = new BufferedReader(new InputStreamReader(stdout));
		writer = new BufferedWriter(new OutputStreamWriter(stdin));

	}

}
