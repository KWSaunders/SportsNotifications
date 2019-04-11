package com.sportsnotifyme.api.util;

import java.io.*;
import java.net.*;

public class WebRequest {

	private String link;


	public WebRequest(String link) {
		this.link = link;
	}


	public String getContent() {
		URL url = null;
		try {
			url = new URL(link);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		InputStream stream = null;
		try {
			stream = url.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			return reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Failed to connect to resource!";
	}

}
