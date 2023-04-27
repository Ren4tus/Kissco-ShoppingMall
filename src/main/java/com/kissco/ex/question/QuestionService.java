package com.kissco.ex.question;

import com.kissco.ex.answer.Answer;
import com.kissco.ex.execption.DataNotFoundException;
import com.kissco.ex.user.SiteUser;
import com.kissco.ex.user.SiteUserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.criteria.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class QuestionService implements WebMvcConfigurer {

    private final QuestionRepository questionRepository;

    private Specification<Question> search(String kw) {
        return new Specification<Question>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용
                        cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용
                        cb.like(u2.get("username"), "%" + kw + "%"));   // 답변 작성자
            }
        };
    }


    private final ModelMapper modelMapper;
    
    private QuestionDto of(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }
    
    private Question of(QuestionDto questionDto) {
        return modelMapper.map(questionDto, Question.class);
    }
    
    public Page<QuestionDto> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Question> spec = search(kw);
        Page<Question> questionList = this.questionRepository.findAll(spec, pageable);
        Page<QuestionDto> questionDtoList = questionList.map(q -> of(q));
        return questionDtoList;
    }
    
    public QuestionDto getQuestion(Integer id) {  
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return of(question.get());
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    String projectPath = "file:" + System.getProperty("user.home") + "/Documents/WEB/files/";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        projectPath = projectPath.replace("\\", "/");
                System.out.println("projectPath = " + projectPath);
        registry.addResourceHandler("/", "/files/")
                .addResourceLocations("classpath:/static/", projectPath);

    }
    public QuestionDto create(String subject, String content, MultipartFile file, SiteUserDto user) throws Exception{
        QuestionDto questionDto = new QuestionDto();
        questionDto.setSubject(subject);
        questionDto.setContent(content);
        questionDto.setCreateDate(LocalDateTime.now());
        questionDto.setAuthor(user);


        if(!file.isEmpty()) {
//            String projectPath = System.getProperty("user.dir") + "\src\main\resources\static\files";
            // String projectPath = "C:/Users/lys/Documents/WEB/files";
            String projectPath = System.getProperty("user.home") + "/Documents/WEB/files";
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);

            file.transferTo(saveFile);
            questionDto.setFilename(fileName);
            questionDto.setFilepath(fileName);
        }

        Question question = of(questionDto);
        this.questionRepository.save(question);
        return questionDto;
    }
    
    public QuestionDto modify(QuestionDto questionDto, String subject, String content) {
        questionDto.setSubject(subject);
        questionDto.setContent(content);
        questionDto.setModifyDate(LocalDateTime.now());
        Question question = of(questionDto);
        this.questionRepository.save(question);
        return questionDto;
    }
    
    public void delete(QuestionDto questionDto) {
        this.questionRepository.delete(of(questionDto));
    }
    
    public QuestionDto vote(QuestionDto questionDto, SiteUserDto siteUserDto) {
        questionDto.getVoter().add(siteUserDto);
        this.questionRepository.save(of(questionDto));
        return questionDto;
    }
}