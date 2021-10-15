package com.learn.dubbo.service;

import com.learn.dubbo.model.ProviderBean;

import java.util.List;

/**
 * RPC接口
 * dubbo使用方式的接口
 * dubbo通过统一的接口进行rpc交互
 */
public interface ProviderService {

    String sayHello(String word);

    List<ProviderBean> getProviderBean();
}
