package com.Comic.ComicRipper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Service;

@Service
public class PreventingIdling {

	
	public void Idling(Timer timer) {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {

				try {
					URL arcJava = new URL("https://link-crawler.herokuapp.com");
					java.net.URLConnection yc = arcJava.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
					String inputLine;

					while ((inputLine = in.readLine()) != null)
						System.out.println(inputLine);
					in.close();

				} catch (MalformedURLException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}, 0, 900000);
	}
}
