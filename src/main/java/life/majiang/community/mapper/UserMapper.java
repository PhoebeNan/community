package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhaoyanan
 * @create 2019-10-21-10:02
 */
@Mapper
public interface UserMapper {

    /**
     * 增加用户
     * @param user
     */
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url)" +
            " values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    /**
     * 通过用户的token查询出所有的用户
     * @param token  用户的token信息
     * @return
     */
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    /**
     * 通过用户id查询所有用户
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User findUserById(@Param("id") Integer id);
}
