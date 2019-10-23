package life.majiang.community.service;

import life.majiang.community.dto.PaginationDto;
import life.majiang.community.dto.QuestionDto;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyanan
 * @create 2019-10-23-9:01
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 问题列表
     * @param currentPage 分页中的当前页码
     * @param pageSize 分页中的当前页的大小，当前页有多少条数据
     * @return
     */
    public PaginationDto list(Integer currentPage, Integer pageSize) {

        //得到偏移量
        Integer offset = pageSize*(currentPage-1);
        List<Question> questions = this.questionMapper.list(offset,pageSize);

        PaginationDto paginationDto = new PaginationDto();
        List<QuestionDto> questionDtos = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findUserById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            //questionDto.setId(question.getCreator());
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtos.add(questionDto);
        }

        //把questionDtos对象存储到paginationDto集合中
        paginationDto.setQuestionDtos(questionDtos);
        Integer totalCount = this.questionMapper.count();
        paginationDto.setPagination(totalCount,currentPage,pageSize);
        return paginationDto;
    }
}