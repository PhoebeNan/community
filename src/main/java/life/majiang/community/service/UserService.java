package life.majiang.community.service;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoyanan
 * @create 2019-10-26-14:17
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void insertOrUpdateUser(User user) {

        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 0) {
            //用同一个gihtub用户不存在，进行增加用户操作
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            User user1 = users.get(0);

            User dbUser= new User();
            //已经存在，进行更新操作
            dbUser.setToken(user.getToken());
            dbUser.setName(user.getName());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setGmtModified(System.currentTimeMillis());

            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdEqualTo(user1.getId());
            userMapper.updateByExampleSelective(dbUser,userExample);
        }
    }
}
