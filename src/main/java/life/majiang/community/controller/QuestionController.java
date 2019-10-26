package life.majiang.community.controller;

import life.majiang.community.dto.QuestionDto;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhaoyanan
 * @create 2019-10-26-10:16
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id, Model model){

        QuestionDto questionDto = this.questionService.getQuestionById(id);
        model.addAttribute("question", questionDto);
        return "question";
    }

}
