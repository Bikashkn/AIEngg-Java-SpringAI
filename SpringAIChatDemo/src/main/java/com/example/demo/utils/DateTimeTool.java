package com.example.demo.utils;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class DateTimeTool {
	
	@Tool(name = "getCurrentDateTime", description = "Get the current date and time")
	/*Write a method that returns the current date and time in the format "yyyy-MM-dd HH:mm:ss" 
	and takes a timezone as a parameter. The timezone should be in the format "America/New_York", 
	"Europe/London", etc.*/
	public String getCurrentDateTime(String timezone) {
		java.time.ZonedDateTime now = java.time.ZonedDateTime.now(java.time.ZoneId.of(timezone));
		java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return now.format(formatter);
	}

}
