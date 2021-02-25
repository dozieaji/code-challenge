package example.com.codechallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class SnippetRequest extends RepresentationModel<SnippetRequest> {

    private String name;
    @JsonProperty("expires_in")
    private long expiresIn;
    private String snippet;

}
