package com.dp.socialmedia.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
    @SequenceGenerator(name = "post_sequence", sequenceName = "post_seq", allocationSize = 10)
    private Long id;
    private String title;
    private String text;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by_user")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @Column(name = "create_date", nullable = false, updatable = false)
    @CreationTimestamp
    private ZonedDateTime createDate;

    @UpdateTimestamp
    private ZonedDateTime updateDate;
}
