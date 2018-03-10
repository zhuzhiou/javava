package cn.javava.weixin.pay.sdk;

import cn.javava.weixin.pay.config.ApiConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

@Service
public class WxPayRequest {

    @Autowired
    private ApiConfig apiConfig;

    private String request(final String url, String data, int connectTimeoutMs, int readTimeoutMs, SSLConnectionSocketFactory sslConnectionSocketFactory) throws Exception {
        BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", sslConnectionSocketFactory)
                        .build(), null, null, null
        );
        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeoutMs).setConnectTimeout(connectTimeoutMs).build();
        httpPost.setConfig(requestConfig);
        StringEntity postEntity = new StringEntity(data, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        //demo中说很重要的，不知道干嘛用
        httpPost.addHeader("User-Agent", "wxpay sdk java v1.0 ");
        httpPost.setEntity(postEntity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity, "UTF-8");
    }

    /**
     * 请求 非证书请求
     *
     * @param url
     * @param data
     * @param connectTimeoutMs
     * @param readTimeoutMs
     * @return
     * @throws Exception
     */
    public String request(final String url, String data, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        return request(url, data, connectTimeoutMs, readTimeoutMs, SSLConnectionSocketFactory.getSocketFactory());
    }

    /**
     * 通过证书请求
     *
     * @param url
     * @param data
     * @param connectTimeoutMs
     * @param readTimeoutMs
     * @param certStream
     * @return
     * @throws Exception
     */
    public String request(final String url, String data, int connectTimeoutMs, int readTimeoutMs, InputStream certStream) throws Exception {
        char[] password = apiConfig.getMchId().toCharArray();
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(certStream, password);
        // 实例化密钥库 & 初始化密钥工厂
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, password);
        // 创建 SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                sslContext, new String[]{"TLSv1"}, null, new DefaultHostnameVerifier());
        return request(url, data, connectTimeoutMs, readTimeoutMs, sslConnectionSocketFactory);
    }

    /**
     * 可重试的，非双向认证的请求
     *
     * @param url
     * @param data
     * @param connectTimeoutMs
     * @param readTimeoutMs
     * @return
     */
    public String requestWithoutCert(String url, String data, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        return this.request(url, data, connectTimeoutMs, readTimeoutMs);
    }

    /**
     * 可重试的，双向认证的请求
     *
     * @param url
     * @param data
     * @param connectTimeoutMs
     * @param readTimeoutMs
     * @return
     */
    public String requestWithCert(String url, String data, int connectTimeoutMs, int readTimeoutMs, InputStream certStream) throws Exception {
        return this.request(url, data, connectTimeoutMs, readTimeoutMs, certStream);
    }
}
