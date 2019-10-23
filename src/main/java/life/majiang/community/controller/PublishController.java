package life.majiang.community.controller;

import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaoyanan
 * @create 2019-10-22-9:02
 */
@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String postPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model) {

        //把前端页面传过来的值再传到前端，进行回显
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        //判断是否为空
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        //判断用户是否是登录状态
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = this.userMapper.findByToken(token);
                    //若用户存在，则将用户信息存储到session中并返回到前端页面
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        break;
                    }
                }
            }
        }

        //若用户存在，则将错误信息放到model对象中并返回到前端页面
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        //将文章的属性数据存储到数据库中
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        this.questionMapper.create(question);
        return "redirect:/";
    }
}
