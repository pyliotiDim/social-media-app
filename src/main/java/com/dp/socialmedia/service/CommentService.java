package com.dp.socialmedia.service;

import com.dp.socialmedia.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    Page<Comment> findAll(Pageable pageable);
    Comment find(Long postId, Long commentId);

    Comment create(Long postId, Comment comment);
    Comment update(Long postId, Comment comment);

    void delete(Long postId, Long commentId);
}
