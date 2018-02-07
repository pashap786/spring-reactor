package com.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.notification.service.NotificationConsumer;

import reactor.Environment;
import reactor.bus.EventBus;
import reactor.core.config.DispatcherType;

import static reactor.bus.selector.Selectors.$;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ApplicationConfig implements CommandLineRunner {
	
	private int REACTOR_THREAD_COUNT = 100;
     
    @Autowired
    private EventBus eventBus;
     
    @Autowired
    private NotificationConsumer notificationConsumer;
     
    @Bean
    Environment env() {
        return Environment.initializeIfEmpty().assignErrorJournal();
    }
     
    @Bean
    EventBus createEventBus(Environment env) {
        return EventBus.create(env, Environment.newDispatcher(REACTOR_THREAD_COUNT,
        		REACTOR_THREAD_COUNT, DispatcherType.THREAD_POOL_EXECUTOR));
    }
 
    public void run(String... args) throws Exception {
        eventBus.on($("notificationConsumer"), notificationConsumer);
    }
 

}