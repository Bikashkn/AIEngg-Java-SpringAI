package com.example.demo.utils;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class NewsTool {
	
	//Method to get news headlines based on a topic from a news API with diffrent parameters like country, language, category, etc.
	 //The method should return a list of news headlines in JSON format.
	 @Tool(name = "getNewsHeadlines", description = "Get news headlines based on a topic from a news API with different parameters like country, language, category, etc.")
	 public String getNewsHeadlines(String topic, String country, String language, String category) {						
		 //Call the news API with the given parameters and return the response in JSON format.
		 //For example, you can use the NewsAPI (https://newsapi.org/) to get news headlines.
		 //You will need to sign up for an API key and use it in your request.
		 //You can use the RestTemplate class from Spring to make the API request.
		 
		 String apiKey = "481db7a44cc04245a595be8b1286c3a7"; //Replace with your API key
		 String url = "https://newsapi.org/v2/top-headlines?q=" + topic + "&country=" + country + "&language=" + language + "&category=" + category + "&apiKey=" + apiKey;
		 
		 org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
		 String response = restTemplate.getForObject(url, String.class);
		 
		 return response;
	 }

}
