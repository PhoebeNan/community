package life.majiang.community.dto;

import lombok.Data;

/**
 * @author zhaoyanan
 * @create 2019-10-16-14:40
 */
@Data
public class AccessTokenDto {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
