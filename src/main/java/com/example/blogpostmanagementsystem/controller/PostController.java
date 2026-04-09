package com.example.blogpostmanagementsystem.controller;

import com.example.blogpostmanagementsystem.dto.PostDto;
import com.example.blogpostmanagementsystem.model.Post;
import com.example.blogpostmanagementsystem.security.JwtUtil;
import com.example.blogpostmanagementsystem.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService service;
    private final JwtUtil jwtUtil;

    // Create
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Post create(
            @ModelAttribute PostDto dto,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestHeader("Authorization") String token) throws Exception {

        String username = jwtUtil.extractUsername(token.substring(7));
        return service.create(dto, image, username);
    }

    // READ ALL
    @GetMapping
    public List<Post> getAll() {
        return service.getAll();
    }

    // SEARCH
    @GetMapping("/search")
    public List<Post> search(@RequestParam(required = false) String keyword) {
        return service.search(keyword);
    }

    // UPDATE
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Post update(
            @PathVariable Long id,
            @ModelAttribute PostDto dto,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestHeader("Authorization") String token) throws Exception {

        String username = jwtUtil.extractUsername(token.substring(7));
        return service.update(id, dto, image, username);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id,
                         @RequestHeader("Authorization") String token) {

        String username = jwtUtil.extractUsername(token.substring(7));
        service.delete(id, username);
        return "Post deleted successfully";
    }
}
