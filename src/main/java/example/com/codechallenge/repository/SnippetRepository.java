package example.com.codechallenge.repository;

import example.com.codechallenge.service.model.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnippetRepository extends JpaRepository<Snippet, String> {

    Snippet findByName(String name);
}
