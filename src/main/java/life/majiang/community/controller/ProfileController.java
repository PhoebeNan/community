package life.majiang.community.controller;

import life.majiang.community.dto.PaginationDto;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaoyanan
 * @create 2019-10-24-11:34
 */
@Controller
public class ProfileController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                          @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize) {

        //通过拦截器存储到session中的user来获取用户信息
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        //若用户第一次访问此页码，则不能执行以下代码，因为还没有登录
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新消息");
        }

        PaginationDto paginationDto = questionService.listUserById(user.getId(), currentPage, pageSize);
        model.addAttribute("pagination", paginationDto);

        return "profile";
    }

}
