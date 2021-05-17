package com.learn;

import com.learn.config.JettyServer;

/**
 * 服务启动类
 */
public class Application {

    public static void main(String[] args) throws Exception {
        new JettyServer().run();
    }
}
