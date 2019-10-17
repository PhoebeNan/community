package life.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDto;
import life.majiang.community.dto.GithubUserDto;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zhaoyanan
 * @create 2019-10-16-14:38
 */
@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDto accessTokenDto) {

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String url = "https://github.com/login/oauth/access_token";

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String s1 = response.body().string();
            String access_token = s1.split("&")[0].split("=")[1];
            return access_token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUserDto getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://api.github.com/user?access_token=";
        Request request = new Request.Builder()
                .url(url+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String user = response.body().string();
            GithubUserDto githubUserDto = JSON.parseObject(user, GithubUserDto.class);
            return githubUserDto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
