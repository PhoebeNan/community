package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

/**
 * @author zhaoyanan
 * @create 2019-10-22-11:37
 */
@Data
public class QuestionDto {

    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCont; //点赞数
    private Integer creator; //创建者

    private User user;

}
