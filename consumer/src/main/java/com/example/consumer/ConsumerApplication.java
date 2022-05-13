package com.example.consumer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumerApplication {
    
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private FeignDemo demo;

    /**
     * 通过Feign调用服务提供者[provider]的接口
     */
    @GetMapping("/get")
    public String feignDemo(){
        return "Consumer: " + demo.getMessage();
    }

    /**
     * KubernetesDiscoveryClient核心类实现了DiscoveryClient
     * 通过getServices()方法可以获取k8s中的服务实例
     */
    @GetMapping("/consumer/index")
    public String indexService() {
        log.info("消费服务:consumer");
        List<String> services = discoveryClient.getServices();
        services.forEach(System.out::println);
        return "消费服务:consumer";
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}