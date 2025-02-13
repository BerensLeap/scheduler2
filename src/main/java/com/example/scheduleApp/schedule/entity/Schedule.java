package com.example.scheduleApp.schedule.entity;

import com.example.scheduleApp.common.entity.BaseEntity;
import com.example.scheduleApp.entity.Comment;
import com.example.scheduleApp.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Range(max = 10)
    private User user;

    @Column(nullable = false)
    @Range(max = 10)
    private String title;

    @Column(columnDefinition = "longtext")
    private String content;

    @OneToMany(mappedBy = "schedule")
    private List<Comment> comments = new ArrayList<>();

    public Schedule(String title, String content, User user) {

    }

    public void update(String title, String content) { // 제목 ,내용 update
        this.title = title;
        this.content = content;
    }
}
