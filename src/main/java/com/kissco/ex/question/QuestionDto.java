package com.kissco.ex.question;

import com.kissco.ex.answer.AnswerDto;
import com.kissco.ex.user.SiteUserDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class QuestionDto {
    private Integer id;
    private String subject;
    private String content;
    private LocalDateTime createDate;
    private List<AnswerDto> answerList;
    private SiteUserDto author;
    private LocalDateTime modifyDate;
    private String filename;
    private String filepath;
    private Set<SiteUserDto> voter;
}
