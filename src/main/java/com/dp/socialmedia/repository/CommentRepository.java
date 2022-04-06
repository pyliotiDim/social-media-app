package com.dp.socialmedia.repository;

import com.dp.socialmedia.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select * from comment c where c.id = :commentId and c.post_id = :postId", nativeQuery = true)
    Optional<Comment> findByPostIdAndCommentId(@Param("postId") Long postId, @Param("commentId") Long commentId);
}
