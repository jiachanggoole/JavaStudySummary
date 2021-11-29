package com.sunny.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sms")
@Data
public class SmsProperties {

    private SmsMessage aliyun = new SmsMessage();

    private SmsMessage tencent = new SmsMessage();

    @Data
    public static class SmsMessage{


        /**
         * 用户名
         */
        private String userName;

        /**
         * 密码
         */
        private String passWord;

        /**
         * 秘钥
         */
        private String sign;

        /**
         *
         */
        private String url;

        @Override
        public String toString() {
            return "SmsMessage{" +
                    "userName='" + userName + '\'' +
                    ", passWord='" + passWord + '\'' +
                    ", sign='" + sign + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}