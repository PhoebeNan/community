package life.majiang.community.controller;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/")
    public String index(HttpServletRequest request) {

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
        return "index";
    }
}
