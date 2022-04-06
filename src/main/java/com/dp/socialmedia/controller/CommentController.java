package com.dp.socialmedia.controller;

import com.dp.socialmedia.dto.CommentDto;
import com.dp.socialmedia.entity.Comment;
import com.dp.socialmedia.mapper.CommentMapper;
import com.dp.socialmedia.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/post/{postId}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper mapper;

    @GetMapping
    public Page<CommentDto> retrieveComments(@PathVariable Long postId, Pageable pageable) {

        Pageable paging = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        return commentService.findAll(pageable).map(mapper::toDto);
    }

    @GetMapping("/{id}")
    public CommentDto retrieveComment(@PathVariable Long postId, @PathVariable Long id) {
        return mapper.toDto(commentService.find(postId, id));
    }

    @PostMapping
    public CommentDto createComment(@PathVariable Long postId, @RequestBody CommentDto comment) {
        Comment entity = mapper.toEntity(comment);
        log.info("Saving new comment to the database");
        return mapper.toDto(commentService.create(postId, entity));
    }

    @PutMapping("/{id}")
    public CommentDto updateComment(@PathVariable Long postId, @PathVariable Long id, @RequestBody CommentDto comment) {
        Comment entity = mapper.toEntity(comment);
        entity.setId(id);
        log.info("Updating comment to the database");
        return mapper.toDto(commentService.update(postId, entity));
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long postId, @PathVariable Long id) {
        log.info("Delete comment with id {} :", id);
        commentService.delete(postId, id);
    }
}
