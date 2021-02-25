package example.com.codechallenge.service.impl;

import example.com.codechallenge.controller.SnippetController;
import example.com.codechallenge.dto.SnippetRequest;
import example.com.codechallenge.dto.SnippetResponse;
import example.com.codechallenge.service.SnippetService;
import example.com.codechallenge.service.rediscache.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Slf4j
public class SnippetServiceImpl implements SnippetService {

    @Autowired
    private CacheService<SnippetRequest> snippetRepository;

    public SnippetResponse saveSnippet(SnippetRequest snippetRequest) {

        Link link = linkTo(methodOn(SnippetController.class).getSnippet(snippetRequest.getName())).withSelfRel();
        long expiresIn = snippetRequest.getExpiresIn();
        if (expiresIn < 1)
            expiresIn = 30;
        SnippetResponse response = new SnippetResponse(snippetRequest,link.getHref(),LocalDateTime.now().plusSeconds(expiresIn).toString());

        boolean b = snippetRepository.saveRecordToRedis(response, response.getName(), expiresIn);
        if (b)
        {
            return response;
        }
        log.warn("Snippet was not save");
        return null;
    }

    public SnippetResponse likeSnippet(String name) {

        SnippetResponse recordFromRedis = snippetRepository.getRecordFromRedis(name, SnippetResponse.class);
        if (recordFromRedis ==null) {
            log.warn("Record not found on the cache");
            return null;
        }
        long likes = recordFromRedis.getLikes();
        recordFromRedis.setLikes(++likes);
        long expiresIn =  30;
        recordFromRedis.setExpires_at(LocalDateTime.now().plusSeconds(expiresIn).toString());
        boolean b = snippetRepository.saveRecordToRedis(recordFromRedis, recordFromRedis.getName(), expiresIn);
        if (b)
        {
            return recordFromRedis;
        }
        log.warn("Snippet was not Updated");
        return null;
    }


    public SnippetResponse getSnippet(String name) {

        SnippetResponse recordFromRedis = snippetRepository.getRecordFromRedis(name, SnippetResponse.class);
        if (recordFromRedis !=null)
        {
            snippetRepository.updateRecordOnRedis(recordFromRedis.getName(),30);
            recordFromRedis.setExpires_at(LocalDateTime.now().plusSeconds(30).toString());
        }
        return recordFromRedis;
    }
}
