package com.dp.socialmedia.mapper;

import com.dp.socialmedia.dto.CommentDto;
import com.dp.socialmedia.entity.Comment;
import com.dp.socialmedia.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentDto toDto(Comment entity) {
        CommentDto dto = new CommentDto();
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateDate(entity.getUpdateDate());
        User user = new User();
        user.setId(entity.getUser().getId());
        dto.setUser(user);
        return dto;
    }

    public Comment toEntity(CommentDto dto) {
        Comment entity = new Comment();
        entity.setText(dto.getText());
        entity.setCreateDate(dto.getCreateDate());
        entity.setUpdateDate(dto.getUpdateDate());
        User user = new User();
        user.setId(dto.getUser().getId());
        entity.setUser(user);
        return entity;
    }

}
