package com.kissco.ex.question;

import com.kissco.ex.answer.Answer;
import com.kissco.ex.user.SiteUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue()
    @Column(name = "question_id")
    private Integer id;

    private String subject;

    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question")
    private List<Answer> answerList;

    @ManyToOne
    private SiteUser author;

    private LocalDateTime modifyDate;

    private String filename;
    private String filepath;

    @ManyToMany
    Set<SiteUser> voter;
}