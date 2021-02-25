package example.com.codechallenge.service;


import example.com.codechallenge.dto.SnippetRequest;
import example.com.codechallenge.dto.SnippetResponse;

public interface SnippetService {

    SnippetResponse saveSnippet(SnippetRequest snippetRequest);
    SnippetResponse likeSnippet(String name);
    SnippetResponse getSnippet(String name);
}
