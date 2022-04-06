package com.dp.socialmedia.dto;

import com.dp.socialmedia.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PostDto {

    private Long id;
    private String title;
    private String text;

    private List<CommentDto> comments = new ArrayList<>();
    private String createdBy;
    private ZonedDateTime createDate;

    private ZonedDateTime updateDate;
}
