package life.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhaoyanan
 * @create 2019-10-14-9:33
 */
@Controller
public class IndexController {

    /**
     * 测试Thymeleaf模板引擎x
     * @param
     * @return
     */
//    @GetMapping("/index")
//    public String greeting(@RequestParam(name = "name",required = false,defaultValue = "World")
//                           String namex, Model model){
//        model.addAttribute("name",namex);
//        return "index";
//    }


    @GetMapping("/")
    public String index(int x){

        return "index";
    }
}
