package com.dp.socialmedia.service;

import com.dp.socialmedia.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Page<Post> findAll(Pageable pageable);
    Post find(Long id);

    Post create(Post post);
    Post update(Post post);

    void delete(Long id);
}
