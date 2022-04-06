package com.dp.socialmedia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dp.socialmedia.entity.Comment;
import com.dp.socialmedia.entity.Post;
import com.dp.socialmedia.repository.CommentRepository;
import com.dp.socialmedia.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class JpaCommentServiceTest {

    @InjectMocks
    private JpaCommentService service;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private PostRepository postRepository;


    /*@Test
    public void testGetAllComments(){
        // given
        List<Comment> comments = createComment();
        when(commentRepository.findAll(Pageable.ofSize(1))).thenReturn(comments);

        // when
        Page<Comment> result = service.findAll(Pageable.ofSize(1));

        // then
        Assertions.assertEquals(comments, result);
    }*/

    @Test
    public void testSaveComment(){
        //given
        when(postRepository.findById(1L)).thenReturn(Optional.of(createPost()));
        Comment comment = new Comment();
        when(commentRepository.save(comment)).thenReturn(comment);

        //when
        Comment result = service.create(1L, comment);

        //then
        assertNotNull(result);
        assertEquals(comment, result);
    }

    @Test
    public void testUpdateComment(){
        //given
        Comment comment = new Comment();
        comment.setId(1L);
        Long postId = 1L;
        when(commentRepository.findByPostIdAndCommentId(postId, comment.getId())).thenReturn(Optional.of(comment));
        when(commentRepository.save(comment)).thenReturn(comment);

        //when
        Comment result = service.update(postId, comment);

        //then
        assertNotNull(result);
        assertEquals(comment, result);
    }

    @Test
    public void testDeleteComment(){
        //given
        Long commentId = 1L;
        Comment comment = new Comment();
        comment.setId(1L);
        Long postId = 1L;
        when(commentRepository.findByPostIdAndCommentId(postId, commentId)).thenReturn(Optional.of(comment));

        //when
        service.delete(postId, commentId);

        //then
        verify(commentRepository, times(1)).delete(comment);
    }

    private Post createPost() {
        Post post = new Post();
        post.setId(1L);
        return post;
    }

    private List<Comment> createComment() {
        Comment comment = new Comment();
        comment.setText("test comment");
        comment.setId(1L);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        return comments;
    }
}
