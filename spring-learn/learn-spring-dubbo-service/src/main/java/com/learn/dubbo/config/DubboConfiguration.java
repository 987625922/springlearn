package com.learn.dubbo.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * dubbo注解方式配置
 */
@Configuration
//指定扫描指定包下所有标注@Service的类
@EnableDubbo(scanBasePackages = "com.learn.dubbo.service.impl")
@ComponentScan(value = {"com.learn.dubbo.service.impl"})
@PropertySource("classpath:zookeeper.properties") //导入资源文件
public class DubboConfiguration {

    @Value("${zookeeper.provide.timeout}")
    private int timeOut;

    @Value("${zookeeper.application.name}")
    private String applicationName;

    @Value("${zookeeper.registry.protocol}")
    private String registryName;

    @Value("${zookeeper.registry.address}")
    private String registryAddress;

    @Value("${zookeeper.registry.port}")
    private int registryPort;

    @Value("${zookeeper.protocol.name}")
    private String protocolName;

    @Value("${zookeeper.protocol.port}")
    private int protocolPort;

    //服务提供者信息配置
    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(timeOut);
        return providerConfig;
    }

    @Bean // #2 分布式应用信息配置
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(applicationName);
        return applicationConfig;
    }

    @Bean // #3 注册中心信息配置
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol(registryName);
        registryConfig.setAddress(registryAddress);
        registryConfig.setPort(registryPort);
        return registryConfig;
    }

    @Bean // #4 使用协议配置，这里使用 dubbo
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(protocolName);
        protocolConfig.setPort(protocolPort);
        return protocolConfig;
    }

}
