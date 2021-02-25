package example.com.codechallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class SnippetResponse {

    private String url;
    private String name;
    @JsonProperty("expires_at")
    private String expires_at;
    private String snippet;
    private  long likes;

    public SnippetResponse(SnippetRequest request,String url,String expires_at) {
        this.url = url;
        this.name = request.getName();
        this.expires_at = expires_at;
        this.snippet = request.getSnippet();
    }
}
