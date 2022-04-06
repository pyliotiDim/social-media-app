package com.dp.socialmedia.dto;

import com.dp.socialmedia.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class CommentDto {

    private Long id;
    private String text;
    private ZonedDateTime createDate;
    private ZonedDateTime updateDate;
    private User user;
}
