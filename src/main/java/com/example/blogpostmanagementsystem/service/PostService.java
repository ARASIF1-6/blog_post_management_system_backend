package com.example.blogpostmanagementsystem.service;

import com.example.blogpostmanagementsystem.dto.PostDto;
import com.example.blogpostmanagementsystem.model.Post;
import com.example.blogpostmanagementsystem.model.User;
import com.example.blogpostmanagementsystem.repository.PostRepository;
import com.example.blogpostmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepo;
    private final UserRepository userRepo;
    private final FileService fileService;

    // Create
    public Post create(PostDto dto, MultipartFile file, String username) throws Exception {

        User user = userRepo.findByUsername(username).orElseThrow();

        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setCreatedAt(LocalDateTime.now());
        post.setAuthor(user);

        if (file != null && !file.isEmpty()) {
            String fileName = fileService.uploadFile(file);
            post.setImageUrl("http://localhost:8083/" + fileName); // ✅ save file name
        }

        return postRepo.save(post);
    }

    // READ ALL
    public List<Post> getAll() {
        return postRepo.findAll();
    }

    // SEARCH
    public List<Post> search(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return postRepo.findAll(); // no keyword → return all
        }
        return postRepo.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
    }

    // UPDATE
    public Post update(Long id, PostDto dto, MultipartFile file, String username) throws Exception {

        Post post = postRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Only author can update
        if (!post.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }

        // Update title & content
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        // Update image if provided
        if (file != null && !file.isEmpty()) {
            String fileName = fileService.uploadFile(file); // use your existing FileService
            post.setImageUrl("http://localhost:8083/" + fileName); // save full URL
        }

        return postRepo.save(post);
    }

    // DELETE
    public void delete(Long id, String username) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Optional: Only author or admin
        if (!post.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }

        postRepo.delete(post);
    }
}
