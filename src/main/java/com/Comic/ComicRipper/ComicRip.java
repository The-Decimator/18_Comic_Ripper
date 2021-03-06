package com.Comic.ComicRipper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ComicRip {

	@Autowired
	CommandLine commandLine;

	@Autowired
	Terminal terminal;

	BufferedReader reader;
	BufferedWriter writer;

	public void rip(MultipartFile links) {
		try {
			terminal.StartProcess();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		reader = terminal.reader;
		writer = terminal.writer;
		new File("./download");
		InputStream link;
		try {
			link = links.getInputStream();
			StringBuilder br = new StringBuilder();
			String command = "";
			int x, j = 1;
			boolean flag = true;

			while ((x = link.read()) != -1) {

				if (x != '\n' && flag) {
					br.append((char) x);
				} else if (!flag) {
					flag = true;
				} else {
					System.out.println(br.toString());
					category(br.toString(), j++);
					br.setLength(0);
					flag = false;
				}
			}
			command = "./rclone-v1.53.0-linux-amd64/rclone --config rclone.conf -P copy ./download linkCrawler:";
			commandLine.commandLineFunction(reader, writer, command);

//			command = "rm -r download/";
//			commandLine.commandLineFunction(reader, writer, command);

			terminal.process = null;
		} catch (IOException e) {

			System.out.println("Retry");
		}

	}

	public void category(String link, int j) {
		try {
			new File("./download/ch" + j + "");
			URL arcJava = new URL(link);
			java.net.URLConnection yc = arcJava.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			// int i = 0;
			while ((inputLine = in.readLine()) != null) {
				if ((inputLine.contains("id=\"album_photo_") && inputLine.contains("src=\""))
						|| (inputLine.contains("id=\"album_photo_") && inputLine.contains("data-original=\""))) {
					try {
//						if (i < 5) {
//							String sub = inputLine.substring(inputLine.indexOf("src=\""));
//							String indiviualPhotoLink = sub.substring(5, sub.indexOf("\" "));
//							i++;
//							indiviualPhoto(indiviualPhotoLink, j);
//						} else {
						String sub = inputLine.substring(inputLine.indexOf("data-original=\""));
						String indiviualPhotoLink = sub.substring(15, sub.indexOf("\" "));
						// i++;
						indiviualPhoto(indiviualPhotoLink, j);
						// }
					} catch (Exception e) {
						System.out.println("Retry");
					}
				}
			}
			in.close();

		}catch(UnknownHostException e) {
			System.out.println("retry");
		}
		catch (MalformedURLException e) {

			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {

			e.printStackTrace();
			System.exit(0);
		}
	}

	public void indiviualPhoto(String link, int i) throws IOException {
		String command = "wget -P ./download/ch" + i + "/ " + link + "";
		commandLine.commandLineFunction(reader, writer, command);
	}
}
