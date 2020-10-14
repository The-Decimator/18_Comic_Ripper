package com.Comic.ComicRipper;

public class Status {

	private Object[] Content;
	private String isalive;
	
	public Status(Object[] line, String isalive) {
		this.Content = line;
		this.isalive = isalive;
	}

	public Object[] getContent() {
		return Content;
	}

	public void setContent(Object[] content) {
		Content = content;
	}

	public String getIsalive() {
		return isalive;
	}

	public void setIsalive(String isalive) {
		this.isalive = isalive;
	}

}
