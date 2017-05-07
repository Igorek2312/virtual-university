package ua.km.khnu.virtual.university.service;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.km.khnu.virtual.university.transfare.LoginForm;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author Igor Rybak
 */
@Component
public class LoginServiceImpl implements LoginService {

    private final CloseableHttpClient httpClient;

    @Autowired
    public LoginServiceImpl(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public String login(LoginForm form) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        StringBuffer url = request.getRequestURL();
        String uri = request.getRequestURI();
        String host = url.substring(0, url.indexOf(uri));

        HttpPost httpPost = new HttpPost(host + "/api/v1/oauth/token");
        String base64 = new String(Base64.getEncoder().encode("webapp:123456".getBytes()));
        httpPost.addHeader("Authorization", "Basic " + base64);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "password"));
        params.add(new BasicNameValuePair("username", form.getUsername()));
        params.add(new BasicNameValuePair("password", form.getPassword()));
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse response = httpClient.execute(httpPost);

        ByteSource byteSource = new ByteSource() {
            @Override
            public InputStream openStream() throws IOException {
                return response.getEntity().getContent();
            }
        };

        return byteSource.asCharSource(Charsets.UTF_8).read();
    }
}
