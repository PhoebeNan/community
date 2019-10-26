package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhaoyanan
 * @create 2019-10-22-11:36
 */
@Mapper
public interface QuestionMapper {

    /**
     * 发布问题
     * @param question
     */
    @Insert("insert into question (title,description,tag,gmt_create,gmt_modified,creator)" +
            " values (#{title},#{description},#{tag},#{gmtCreate},#{gmtModified},#{creator})")
    void create(Question question);

    /**
     * 查询所有问题(包括不同的用户)
     * @param offset
     * @param pageSize
     * @return
     */
    @Select("select * from question limit #{offset},#{pageSize}")
    List<Question> list(@Param("offset") Integer offset,
                        @Param("pageSize") Integer pageSize);

    /**
     * 查询所有问题总条数(包括不同的用户)
     * @return
     */
    @Select("select count(1) from question")
    Integer count();

    /**
     * 通过用户的creator查询出不同用户发布的所有问题(同一个用户)
     * @param userId
     * @param offset
     * @param pageSize
     * @return
     */
    @Select("select * from question where creator=#{userId} limit #{offset},#{pageSize}")
    List<Question> listUserById(@Param("userId") Integer userId,
                                @Param("offset") Integer offset,
                                @Param("pageSize") Integer pageSize);

    @Select("select * from question")
    List<Question> listQuestions();

    /**
     * 查询某一用户下所有的问题总条数
     * @param userId
     * @return
     */
    @Select("select count(1) from question where creator=#{userId}")
    Integer countUserById(@Param("userId") Integer userId);
}
