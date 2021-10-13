package com.learn.dubbo.service;

/**
 * dubbo使用方式的接口
 * dubbo通过统一的接口进行rpc交互
 */
public interface ProviderService {
    String sayHello(String word);
}
