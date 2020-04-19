package com.sundi.springbootdemo4.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangyubing
 * @date 2020/04/08
 */
@Slf4j
public class DefaultAlipayClientFactory {

    public static AlipayClient alipayClient = null;
    // 网关
    public static final String URL = "https://openapi.alipay.com/gateway.do";
    // 商户APP_ID
    public static final String APP_ID = "2021001107619207";
    // 商户RSA 私钥
    public static final String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQChZybeMbvVqSAoh3LN8GPLtYliCTZqidP389UqfEF0Bx22Nz+e3SmJ+MoQTnp98wVQi0j41zH4dOdkQWOqbJu3EC2BTYwmbtQDRHf9OOIJItMT2xjP1rH/Qkm1ETFLAj2UlOj13w2/Ep0s9KqSSrf64PrX0nKH0qByNxcZyw38V7505u0pV1oAOTZESjJeMWJWpTdCENFMJRDnujKNzgIMZ4W9/WOFVknQ4d2CPY0eCt7FZFTCFF1Gd/Bz+FbBHlxHUXY+YRvGlCRo57vJiJ1E7ZuMCWcp4S0IjqR4zMV/L8ZK+xm9DyKdqe3QcbTph5oXovGRb8Clj7Fvls0PaRSFAgMBAAECggEAbmvHp5OW0eHW4mNoI4QmgiM8ThnHZLPOlH/4ggxoKx0DMroxiFn+JSpT8zv9iVVNeYEQ900iHry74Vbq/Jk5HtCukRugmlqQKXwaKt9TpLRye88U9nwHYn7TNlEAzDRINPJ7PlrPaL0vUqZAW71KiRSKCEUtncZ3hiwyXa8RbmTy9KdnCqyGpDuO/NYV/4lEvxCwZJiYaWqCAOUmOdCFNuiNEKh5bb1ZYoVkZexN2uHWldIujul0wjD0qlZimh2StVrAv88c8LZ0ZWFqttCbX8poHEneNiajzXAcR7L6LrG5UHlN4b32CTx5W/x7htLVjCV/3KYIyz0UkBh2lYhZAQKBgQDPMnNih2PbNKfxjDgOfeU3BfU4RnOh624L8+WSlvpID2CQs1ZtQF9+YxIGcd6JGC0IUzzp3ucuoegskrIuRq2PcTAvaSs/6yH5fhhIJ1V7hzKRzxEyVf+TqoSlYxDIDzygXIrLl0KCIERUEO5yX7YsSHwzdALi5FS65zmEUpRN9QKBgQDHa2kg9/ERHnWvAi019IlxrfnKj31Rt0HcEw5X78ESoQW/NCQ4iPBzUVDohEYlQgxroX6RdLSG2SZHgQOT4uGkU5kTtHuyEC8m/WnjFeeo8cr65oCETnmaWVWyfBRGJX7xwUtWsd7agUPgLif53olY/Gxa78hTzR5et23lPY6CUQKBgB0wvWLHMkFCtNpp58fa4r0SwM5x1n6cXX+fl9tEG/Sk/fTE30s7A3kZ2gNAbZqg5k9C8tT8C1PdiYkmDaZJXNBVoPU8+tPhe4Lbye34ZKmnjW9PXdvak8wo/NEUGNwmkWMCRqeSItOUWiwByqYgZ9jrSVwyXum6Q1mlk8vZmn01AoGAEchOM5ZodLJWpCh5h5oqt2xgHeP1tNe2k71PRiAnA1ZAgPBRWok7h8z0fgdtidz95oscEjRp9KxgcXfbrdBJdrHNsKgjb5lp2vw1+P33DmWhSAZN535n8Y+NyMCvviGR1FT8NtrdmHZ2awMypWFmtfysXN+M6Ywc7S1GK271ykECgYApMNaKgXol6bFH1+Q30yTXWerFOZ6x4cV3iMYKvp7yQVaZHfBRkDln8WFZrHeu6M6wRPtGWc3lV/4KSWQUlb/pX84kGdDj7Snt9nlftykWoR672oIdILxUVRnpRHALJXkhOzCFwDV5NECzYRo32wkpw34DPAARkSp3GtwInl6lxA==";
    // 请求方式 json
    public static final String FORMAT = "json";
    // 编码格式，目前只支持UTF-8
    public static final String CHARSET = "utf-8";
    // 支付宝公钥
    private static final String ALIPAY_PUBLIC_KEY = "-----BEGIN CERTIFICATE-----\n" +
            "MIIDrzCCApegAwIBAgIQICAECeHUelw2n7CzvVrINDANBgkqhkiG9w0BAQsFADCBgjELMAkGA1UE\n" +
            "BhMCQ04xFjAUBgNVBAoMDUFudCBGaW5hbmNpYWwxIDAeBgNVBAsMF0NlcnRpZmljYXRpb24gQXV0\n" +
            "aG9yaXR5MTkwNwYDVQQDDDBBbnQgRmluYW5jaWFsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5IENs\n" +
            "YXNzIDIgUjEwHhcNMjAwNDA5MTIzMTQ2WhcNMjIwNDA5MTIzMTQ2WjCBjzELMAkGA1UEBhMCQ04x\n" +
            "KjAoBgNVBAoMIeWOpumXqOWNl+ivu+Wutui0uOaYk+aciemZkOWFrOWPuDEPMA0GA1UECwwGQWxp\n" +
            "cGF5MUMwQQYDVQQDDDrmlK/ku5jlrp0o5Lit5Zu9Kee9kee7nOaKgOacr+aciemZkOWFrOWPuC0y\n" +
            "MDg4MzMxMDIzODYzMDg3MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwF7QsfLccLyv\n" +
            "kI8/56Mwv4PvhVOb0+bMs+D7kzH+PANcPgPnlm3sFD/ArC30z515dwQTo4zjyCaxwTsiBZZmryx6\n" +
            "eigxVmP8dSBZCzeI7OQCqdZ5qSFZdYNKpsmqoAO4fYNA8kAjJnp9bYxxMxqzP83JnPo0/+ZUHxmB\n" +
            "5YMPpYNgQWYGAeF3NPujm4jizxCEpti8NxdRnqVVPupLKchlLfvQ4L8E9GVlCmdFPa5ygJJXobYP\n" +
            "fugY/RbwaFPBNZHdm4o4df9MqxplTf178KNi8YUE0snVGm2t0dTFp7wXUnmnIFf2y7OXm0fDNKxh\n" +
            "pQJ4nsMhUBgaSyc3N5YpTUCzJwIDAQABoxIwEDAOBgNVHQ8BAf8EBAMCA/gwDQYJKoZIhvcNAQEL\n" +
            "BQADggEBADn1xwVSd88UyllMCX662ssbZLhhfsrqkcmBoN1ZXXclisrD8H85Un3V0ul8nqRgDV0R\n" +
            "dG/ucU6NNiCfXNWsukNL+uiYel22LOv3dcaZ9sa03Pwpz4iM8Z6vJ3gt/zTM6Tws2fS9dyPgLDhf\n" +
            "UktJ7G5zXXD6lwTinbVHWX4a7KhBkIUl5y57IrI+gbatsj2/blw4ZXdYIpkWUE8NCV7qQvHrVyYb\n" +
            "Ru2u+Gsl9zCJmYew6Qa06B9yqpfUzaP42lsgxKo33CQWQK5LIZA06rHXtdismWEZuBI9BdUeUX3e\n" +
            "4TVCk2YtfoQJQsy2XfvGzYZviJgXSl2/tmgyfuGVGS2N8Xw=\n" +
            "-----END CERTIFICATE-----";
    // 签名方式
    public static final String SIGN_TYPE = "RSA2";

    // 用户号
    public static final String payee_user_id = "2088431029943203";
    // 登录号 邮箱或者手机号
    public static final String payee_logon_id = "1832252548@qq.com";

    public static final String pay_timeout = "2d";

        public static final String CERT_PATH = DefaultAlipayClientFactory.class.getClassLoader().getResource("certificate/appCertPublicKey.crt").getPath();
//    public static final String CERT_PATH = "C:\\Users\\Administrator\\Desktop\\certificate\\appCertPublicKey.crt";
        public static final String ALIPAY_PUBLIC_CERT_PATH = DefaultAlipayClientFactory.class.getClassLoader().getResource("certificate/alipayCertPublicKey_RSA2.crt").getPath();
//    public static final String ALIPAY_PUBLIC_CERT_PATH = "C:\\Users\\Administrator\\Desktop\\certificate\\alipayCertPublicKey_RSA2.crt";
        public static final String ROOT_CERT_PATH = DefaultAlipayClientFactory.class.getClassLoader().getResource("certificate/alipayRootCert.crt").getPath();
//    public static final String ROOT_CERT_PATH = "C:\\Users\\Administrator\\Desktop\\certificate\\alipayRootCert.crt";

    // 门店编号
    public static String ALIPAY_EXTRA_PARAM = "{\"outStoreCode\":\"001\"}";
    public static String OUT_STORECODE = "001";


    static {
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        //设置网关地址
        certAlipayRequest.setServerUrl(URL);
        //设置应用Id
        certAlipayRequest.setAppId(APP_ID);
        //设置应用私钥
        certAlipayRequest.setPrivateKey(APP_PRIVATE_KEY);
        //设置请求格式，固定值json
        certAlipayRequest.setFormat(FORMAT);
        //设置字符集
        certAlipayRequest.setCharset(CHARSET);
        //设置签名类型
        certAlipayRequest.setSignType(SIGN_TYPE);
        //设置应用公钥证书路径
        certAlipayRequest.setCertPath(CERT_PATH);
        //设置支付宝公钥证书路径
        certAlipayRequest.setAlipayPublicCertPath(ALIPAY_PUBLIC_CERT_PATH);
        //设置支付宝根证书路径
        certAlipayRequest.setRootCertPath(ROOT_CERT_PATH);
        //构造client
        try {
            alipayClient = new DefaultAlipayClient(certAlipayRequest);
        } catch (AlipayApiException e) {
            log.error("初始化alipayClient失败");
            e.printStackTrace();
        }
    }

    /**
     * 封装公共请求参数
     *
     * @return AlipayClient
     */
    public static AlipayClient getAlipayClient() {
        return alipayClient;
    }
}
