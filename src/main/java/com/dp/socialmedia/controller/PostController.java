package com.dp.socialmedia.controller;

import com.dp.socialmedia.dto.PostDto;
import com.dp.socialmedia.entity.Post;
import com.dp.socialmedia.mapper.PostMapper;
import com.dp.socialmedia.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper mapper;

    @GetMapping
    public Page<PostDto> retrievePosts(Pageable pageable) {
        return postService.findAll(pageable).map(mapper::toDto);
    }

    @GetMapping("/{id}")
    public PostDto retrievePosts(@PathVariable Long id) {
        return mapper.toDto(postService.find(id));
    }

    @PostMapping
    public PostDto createPost(@RequestBody PostDto post) {
        Post entity = mapper.toEntity(post);
        log.info("Saving new post to the database");
        return mapper.toDto(postService.create(entity));
    }

    @PutMapping("/{id}")
    public PostDto updatePost(@PathVariable Long id, @RequestBody PostDto post) {
        Post entity = mapper.toEntity(post);
        entity.setId(id);
        log.info("Updating post to the database");
        return mapper.toDto(postService.update(entity));
    }
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        log.info("Delete post with id {} :", id);
        postService.delete(id);
    }
}
