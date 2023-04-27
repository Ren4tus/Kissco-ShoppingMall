package com.kissco.ex.answer;

import com.kissco.ex.question.Question;
import com.kissco.ex.user.SiteUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue()
    @Column(name = "answer_id")
    private Integer id;

    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private Question question;
    
    @ManyToOne
    @JoinColumn(name = "question_id")
    private SiteUser author;
    
    private LocalDateTime modifyDate;
    
    @ManyToMany
    Set<SiteUser> voter;
}