package life.majiang.community.model;

import lombok.Data;

/**
 * @author zhaoyanan
 * @create 2019-10-22-11:37
 */
@Data
public class Question {

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

}
