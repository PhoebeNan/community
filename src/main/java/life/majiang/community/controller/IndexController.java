package life.majiang.community.controller;

import life.majiang.community.dto.PaginationDto;
import life.majiang.community.dto.QuestionDto;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhaoyanan
 * @create 2019-10-14-9:33
 */
@Controller
public class IndexController {

    /**
     * 测试Thymeleaf模板引擎x
     *
     * @param
     * @return
     */
//    @GetMapping("/index")
//    public String greeting(@RequestParam(name = "name",required = false,defaultValue = "World")
//                           String namex, Model model){
//        model.addAttribute("name",namex);
//        return "index";
//    }

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "currentPage",defaultValue = "1") Integer currentPage,
                        @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize) {

        //通过cookie查看数据库是否存在当前用户
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        //在index页面中展示问题列表
        PaginationDto pagination = questionService.list(currentPage, pageSize);
        model.addAttribute("pagination", pagination);

        return "index";
    }
}
