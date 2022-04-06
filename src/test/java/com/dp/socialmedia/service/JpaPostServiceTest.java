package com.dp.socialmedia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.dp.socialmedia.entity.Post;
import com.dp.socialmedia.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class JpaPostServiceTest {

    @InjectMocks
    private JpaPostService service;
    @Mock
    private PostRepository repository;

    @Test
    public void testFindPost() {
        //given
        Post post = createPost();
        when(repository.findById(post.getId())).thenReturn(Optional.of(post));

        //when
        Post result = service.find(post.getId());

        //then
        assertNotNull(result);
        assertEquals(post, result);
    }

    @Test
    public void testCreatePost() {
        //given
        Post post = createPost();
        when(repository.save(post)).thenReturn(post);

        //when
        Post result = service.create(post);

        //then
        assertNotNull(result);
        assertEquals(post, result);
    }


    @Test
    public void testUpdateComment(){
        //given
        Post post = createPost();
        when(repository.findById(post.getId())).thenReturn(Optional.of(post));
        when(repository.save(post)).thenReturn(post);

        //when
        Post result = service.update(post);

        //then
        assertNotNull(result);
        assertEquals(post, result);
    }


    private Post createPost() {
        Post post = new Post();
        post.setId(1L);
        return post;
    }

}
