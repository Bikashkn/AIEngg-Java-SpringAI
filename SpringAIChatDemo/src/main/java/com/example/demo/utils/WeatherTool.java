package com.example.demo.utils;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class WeatherTool {
	
	//Method to get weather information based on a city name from a weather API.
	//The method should return the weather information in JSON format.
	//You can use the OpenWeatherMap API (https://openweathermap.org/api) to get weather information.
	//You will need to sign up for an API key and use it in your request.
	@Tool(name = "getWeatherInfo", description = "Get weather information based on a city name from a weather API")
	public String getWeatherInfo(String cityName) {
		String apiKey = "4dbe527fa6ee4596947111721262706"; //Replace with your API key
		String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey;
		
		org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
		String response = restTemplate.getForObject(url, String.class);
		
		return response;
	}
}
