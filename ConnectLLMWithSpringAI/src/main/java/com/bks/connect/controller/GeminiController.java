package com.bks.connect.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller that exposes a simple HTTP endpoint for sending a user message to
 * the Google Gemini (GenAI) chat model and returning the model's textual response.
 *
 * <p>Endpoint: GET /api/gemini/{message}</p>
 *
 * <p>Notes:
 * - A {@link GoogleGenAiChatModel} bean must be configured in the Spring context; it is
 *   injected into the constructor and used to build a {@link ChatClient}.
 * * - The controller reuses a single, final {@link ChatClient} instance for forwarding requests.
 *</p>
 *
 * <p>Thread-safety: the controller holds a final {@code chatClient} reference which is safe
 * to reuse for simple request forwarding.</p>
 */
@RestController
@RequestMapping("/api/gemini")
@CrossOrigin("*")
public class GeminiController {

    /**
     * Chat client used to send prompts to the Gemini model. Built from the injected
     * {@link GoogleGenAiChatModel} and reused across requests.
     */
    private final ChatClient chatClient;

    /**
     * Construct the controller.
     *
     * @param googleGenAiChatModel a configured Google Gemini chat model provided by Spring
     *                             which is used to build the {@link ChatClient}
     */
    public GeminiController(GoogleGenAiChatModel googleGenAiChatModel) {

        this.chatClient = ChatClient
                .builder(googleGenAiChatModel)
                .build();
    }

    /**
     * Forward the provided message to Gemini and return the model's textual response.
     *
     * Example request: GET /api/gemini/Hello%20Gemini
     *
     * @param message the user message (taken from the path variable; it should be URL-encoded by the client)
     * @return the textual content returned by the Gemini model
     */
    @GetMapping("/{message}")
    public String ask(@PathVariable String message) {

        // Simple console log for visibility in examples; consider replacing with a proper logger.
        System.out.println("Calling Google Gemini !!!");

        return chatClient
                .prompt()
                .user(message)
                .call()
                .content();
    }
}