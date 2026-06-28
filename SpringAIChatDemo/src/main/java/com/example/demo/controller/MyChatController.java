package com.example.demo.controller;

import java.util.UUID;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.utils.DateTimeTool;
import com.example.demo.utils.NewsTool;
import com.example.demo.utils.WeatherTool;

/**
 * REST controller for handling chat interactions.
 * This controller exposes an endpoint for users to send prompts and receive responses from an AI chat model.
 * It integrates with various tools to provide enhanced functionality like fetching date/time, news, and weather.
 */
@RestController
@RequestMapping("/my-gpt")
@CrossOrigin("*")
public class MyChatController {
	
	/**
	  * The main client for interacting with the AI chat model.
	  */
	 private ChatClient chatClient;
	 
	 /**
	  * A tool for fetching the current date and time.
	  */
	 @Autowired
	 private DateTimeTool dateTimeTool;

	 /**
	  * A tool for fetching the latest news.
	  */
	  @Autowired
	  private NewsTool newsTool;
	  
	  /**
	   * A tool for fetching weather information.
	   */
	  @Autowired
	  private WeatherTool weatherTool;
	 
	
	  /**
	   * In-memory storage for the chat conversation history.
	   */
	  ChatMemory chatMemory = MessageWindowChatMemory.builder().build();
	  /**
	   * A unique identifier for the current conversation session.
	   */
	   String conversationId= UUID.randomUUID().toString();
	   
	   /**
		   * Constructs the MyChatController and initializes the ChatClient.
		   * The ChatClient is configured with chat memory to maintain conversation context
		   * and a unique conversation ID.
		   *
		   * @param builder The ChatClient builder provided by Spring AI.
		   */      
	  public MyChatController(ChatClient.Builder builder)
	        {
	            this.chatClient=builder.build();
	            this.chatClient=builder
	                    .defaultAdvisors(MessageChatMemoryAdvisor
	                            .builder(chatMemory)
	                            .build()).defaultAdvisors(a ->
	                            a.param(ChatMemory.CONVERSATION_ID, conversationId))
	                    .build();
	        }
	
	 
	  /**
		 * Handles GET requests to the /chat/{prompt} endpoint.
		 * It takes a user's prompt, sends it to the chat model along with available tools,
		 * and returns the model's response.
		 *
		 * @param prompt The user's input string.
		 * @return A string containing the model's name and the generated text response.
		 */
	@GetMapping("/chat/{prompt}")
	public String getInfo(@PathVariable String prompt)
	    {
	        ChatResponse chatResponse = chatClient.prompt(prompt)
	                .tools(dateTimeTool,newsTool,weatherTool)
	                .call()
	                .chatResponse();
	        //System.out.println(chatResponse.getMetadata().getModel());
	        return chatResponse
	        		.getMetadata().getModel() + "\n" + chatResponse
	                .getResult()
	                .getOutput()
	                .getText();
	    }

}
