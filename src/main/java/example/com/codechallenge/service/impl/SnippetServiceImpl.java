package example.com.codechallenge.service.impl;

import example.com.codechallenge.controller.SnippetController;
import example.com.codechallenge.dto.SnippetRequest;
import example.com.codechallenge.dto.SnippetResponse;
import example.com.codechallenge.repository.SnippetRepository;
import example.com.codechallenge.service.SnippetService;
import example.com.codechallenge.service.model.Snippet;
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
    private SnippetRepository snippetRepository;

    public SnippetResponse saveSnippet(SnippetRequest snippetRequest) {

        Snippet snippet = snippetRepository.findByName(snippetRequest.getName());

        Link link = linkTo(methodOn(SnippetController.class).getSnippet(snippetRequest.getName())).withSelfRel();
        long expiresIn = snippetRequest.getExpiresIn();
        if (expiresIn < 1)
            expiresIn = 30;
        if (snippet == null)
            snippet = new Snippet(snippetRequest, link.getHref(), LocalDateTime.now().plusSeconds(expiresIn));
        else snippet.setExpiresAt(LocalDateTime.now().plusSeconds(expiresIn));
        snippet = snippetRepository.save(snippet);

        return new SnippetResponse(snippet);

    }

    public SnippetResponse likeSnippet(String name) {

        Snippet snippet = snippetRepository.findByName(name);
        if (snippet == null) {
            log.warn("Record not found on the cache");
            return null;
        }
        long likes = snippet.getLikes();
        snippet.setLikes(++likes);
        long expiresIn = 30;
        snippet.setExpiresAt(LocalDateTime.now().plusSeconds(expiresIn));
        snippet = snippetRepository.save(snippet);

        return new SnippetResponse(snippet);

    }


    public SnippetResponse getSnippet(String name) {

        Snippet snippet = snippetRepository.findByName(name);
        if (snippet == null)
            return null;
        LocalDateTime expiresAt = snippet.getExpiresAt();
        LocalDateTime now = LocalDateTime.now();
        log.info("Expired " + expiresAt);
        log.info("Now " + now);
        if (expiresAt.plusSeconds(30).isBefore(now)) {
            log.info("Link has expired");
            return null;
        }
        snippet.setExpiresAt(LocalDateTime.now().plusSeconds(30));
        return new SnippetResponse(snippet);
    }
}
