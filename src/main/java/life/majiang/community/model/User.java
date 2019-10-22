package life.majiang.community.model;

import lombok.Data;

/**
 * @author zhaoyanan
 * @create 2019-10-21-9:59
 */
@Data
public class User {
    private int id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
