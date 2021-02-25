package example.com.codechallenge.controller;


import example.com.codechallenge.dto.SnippetRequest;
import example.com.codechallenge.dto.SnippetResponse;
import example.com.codechallenge.service.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/snippets")
public class SnippetController {

    @Autowired
    private SnippetService snippetService;

    @PostMapping
    public ResponseEntity<Object> saveSnippet(@RequestBody SnippetRequest snippetRequest) {
        SnippetResponse snippetResponse = snippetService.saveSnippet(snippetRequest);

        return new ResponseEntity<>(snippetResponse, HttpStatus.CREATED);

    }

    @PostMapping("{name}/like")
    public ResponseEntity<Object> likeSnippet(@PathVariable("name") String name) {
        SnippetResponse snippetResponse = snippetService.likeSnippet(name);
        System.out.println("snippet" + snippetResponse);
        return new ResponseEntity<>(snippetResponse, HttpStatus.OK);

    }

    @GetMapping("/{name}")
    public ResponseEntity<Object> getSnippet(@PathVariable("name") String name) {
        SnippetResponse response = snippetService.getSnippet(name);

        if (response == null)
            return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(response);

    }

}
