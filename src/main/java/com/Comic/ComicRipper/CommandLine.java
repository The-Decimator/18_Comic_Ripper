package com.Comic.ComicRipper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandLine {

	@Autowired
	Terminal terminal;

	public void commandLineFunction(BufferedReader reader, BufferedWriter writer, String command) throws IOException {
		writer.write("((" + command + ") && echo --EOF--) || echo --EOF--\n");
		writer.flush();

		String line = reader.readLine();

		while (line != null && !line.trim().equals("--EOF--")) {
			System.out.println(line);

			terminal.flag = true;
			terminal.Contents.add(line);
			line = reader.readLine();
		}

		if (line == null) {
			terminal.flag = false;
			return;
		}
	}

}
