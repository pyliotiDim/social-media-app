package com.dp.socialmedia.mapper;

import com.dp.socialmedia.dto.CommentDto;
import com.dp.socialmedia.dto.PostDto;
import com.dp.socialmedia.entity.Comment;
import com.dp.socialmedia.entity.Post;
import com.dp.socialmedia.entity.User;
import com.dp.socialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostMapper {

    private final CommentMapper commentMapper;
    private final UserService userService;

    public PostDto toDto(Post entity) {
        PostDto dto = new PostDto();
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setTitle(entity.getTitle());
        List<CommentDto> commentDtos = entity.getComments().stream().map(commentMapper::toDto).collect(Collectors.toList());
        dto.setComments(commentDtos);
        dto.setCreatedBy(entity.getUser().getUsername());
        return dto;
    }

    public Post toEntity(PostDto dto) {
        Post entity = new Post();
        entity.setText(dto.getText());
        entity.setCreateDate(dto.getCreateDate());
        entity.setUpdateDate(dto.getUpdateDate());
        entity.setTitle(dto.getTitle());
        entity.setUser(userService.getCurrentUser());
        Function<CommentDto, Comment> commentFunction = commentDto -> {
            var comment = commentMapper.toEntity(commentDto);
            comment.setPost(entity);
            return comment;
        };
        List<Comment> comments = dto.getComments().stream().map(commentFunction).collect(Collectors.toList());
        entity.setComments(comments);
        return entity;
    }
}
