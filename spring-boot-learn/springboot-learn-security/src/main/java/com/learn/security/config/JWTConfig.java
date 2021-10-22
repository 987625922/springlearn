package com.learn.security.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * JWT配置类
 */
@Getter
@Component
@ConfigurationProperties(prefix = "jwt")
//@Configuration
public class JWTConfig {
    /**
     * 密钥KEY
     */
//    @Value("${jwt.secret}")
    public static String secret;
    /**
     * TokenKey
     */
//    @Value("${jwt.tokenHeader}")
    public static String tokenHeader;
    /**
     * Token前缀字符
     */
//    @Value("${jwt.tokenPrefix}")
    public static String tokenPrefix;
    /**
     * 过期时间
     */
//    @Value("${jwt.expiration}")
    public static Integer expiration;
    /**
     * 不需要认证的接口
     */
//    @Value("${jwt.antMatchers}")
    public static String antMatchers;


    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration * 1000;
    }

    public void setAntMatchers(String antMatchers) {
        this.antMatchers = antMatchers;
    }
}