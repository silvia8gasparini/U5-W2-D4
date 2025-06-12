package it.epicode.U5W2D4practice.repository;

import it.epicode.U5W2D4practice.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost,Integer> {
}
