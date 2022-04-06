package com.dp.socialmedia.service;

import com.dp.socialmedia.entity.Post;
import com.dp.socialmedia.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JpaPostService implements PostService {

    private final PostRepository repository;

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Post find(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    @Transactional
    public Post create(Post post) {
        return repository.save(post);
    }

    @Override
    @Transactional
    public Post update(Post post) {
        find(post.getId());
        return repository.save(post);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
