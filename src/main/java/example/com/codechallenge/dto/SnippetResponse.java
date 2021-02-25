package example.com.codechallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import example.com.codechallenge.service.model.Snippet;
import lombok.Data;

@Data

public class SnippetResponse {

    private String url;
    private String name;
    @JsonProperty("expires_at")
    private String expires_at;
    private String snippet;
    private  long likes;

    public SnippetResponse(Snippet snippet) {
        this.url = snippet.getUrl();
        this.name = snippet.getName();
        this.expires_at = snippet.getExpiresAt().toString();
        this.snippet = snippet.getSnippets();
        this.likes = snippet.getLikes();
    }
}
