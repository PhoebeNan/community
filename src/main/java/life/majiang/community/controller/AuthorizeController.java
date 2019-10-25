package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDto;
import life.majiang.community.dto.GithubUserDto;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author zhaoyanan
 * @create 2019-10-16-14:18
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {

        AccessTokenDto accessTokenDto = new AccessTokenDto();

        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);



        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        System.out.println(accessToken);
        GithubUserDto githubUserDto = githubProvider.getUser(accessToken);
        System.out.println(githubUserDto.getName());
        if (githubUserDto != null && githubUserDto.getId() != null) {

            //把GitHub用户的信息存储到数据库中
            User user = new User();
            user.setAccountId(String.valueOf(githubUserDto.getId()));
            user.setName(githubUserDto.getName());

            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUserDto.getAvatarUrl());

            userMapper.insert(user);

            response.addCookie(new Cookie("token", token));
            //登录成功，写入Session和Cookie
            //request.getSession().setAttribute("user", githubUserDto);
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }
    }
}
