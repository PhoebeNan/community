package life.majiang.community.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author zhaoyanan
 * @create 2019-10-16-16:20
 */
@Component
@Data
public class GithubUserDto {

    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;

}
