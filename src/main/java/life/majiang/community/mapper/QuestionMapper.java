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
    @Insert("insert into question (title,description,tag,gmt_create,gmt_modified,creator)" +
            " values (#{title},#{description},#{tag},#{gmtCreate},#{gmtModified},#{creator})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{pageSize}")
    List<Question> list(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("select count(1) from question")
    Integer count();
}
