package example.com.codechallenge.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import example.com.codechallenge.dto.SnippetRequest;
import example.com.codechallenge.utils.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Snippet {
    @Id
    private String id;
    private String url;
    private String name;
    private LocalDateTime expiresAt;
    private String snippets;
    private  long likes;

    public Snippet(SnippetRequest request, String url, LocalDateTime expiresAt) {
        this.id = UUID.randomUUID().toString();
        this.url = url;
        this.name = request.getName();
        this.expiresAt = expiresAt;
        this.snippets = request.getSnippet();
    }

    public Snippet(SnippetRequest request, Snippet snippet, String url, LocalDateTime expiresAt) {

        this.name = request.getName();
        this.expiresAt = expiresAt;
        this.snippets = request.getSnippet();
    }

//    public String getExpiresAt() {
//        return expiresAt !=null ? CommonUtils.dateToStringFormated(expiresAt) : " ";
//    }

}
