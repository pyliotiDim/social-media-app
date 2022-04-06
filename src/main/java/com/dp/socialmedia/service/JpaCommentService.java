package com.dp.socialmedia.service;

import com.dp.socialmedia.entity.Comment;
import com.dp.socialmedia.repository.CommentRepository;
import com.dp.socialmedia.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class JpaCommentService implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository repository;

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Comment find(Long postId, Long commentId) {
        return repository.findByPostIdAndCommentId(postId, commentId).orElseThrow(() -> new RuntimeException());
    }

    @Override
    @Transactional
    public Comment create(Long postId, Comment comment) {
        var post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException());
        post.getComments().add(comment);
        Comment saved = repository.save(comment);
        post.getComments().add(saved);
        postRepository.save(post);
        return saved;
    }

    @Override
    @Transactional
    public Comment update(Long postId, Comment comment) {
        find(postId, comment.getId());
        return repository.save(comment);
    }

    @Override
    @Transactional
    public void delete(Long postId, Long commentId) {
        var comment = find(postId, commentId);
        repository.delete(comment);
    }
}
