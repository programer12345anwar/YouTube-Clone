package com.youtube.central.configuration;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public CachingConnectionFactory getConnectionFactory() { 
        //CachingConnection factory is nothing but a connection to rabbitmq
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }
    @Bean
    public RabbitTemplate getRabbitTemplate(CachingConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
        
    }
   
    // public static final String queueName = "notification-queue";
    // public static final String exchangeName = "notification-exchange";
    // public static final String routingKey = "notification-123";

    // @Bean
    // public AMQP.Queue queue() {
    //     return new AMQP.Queue();
    // }
}
 /*
     * for explaining @Bean
     * @Autowired
     * RestTemplate restTemplate;//spring boot will not able to provide the object for the restTemplate b'z RestTemplate doesn't have @Component,@Service, @Repository, @Controller,so you need to configure it.
     * similarly
     * @Autowired
     * ArrayList<> arrayList;//similarly also ArrayList doesn't have @Component,@Service, @Repository, @Controller,so you need to configure it.
     * solution: if you want bean in ioc cntainer then use @Bean
     * example:
     * @Bean
     * public RestTemplate restTemplate() {
     *     return new RestTemplate();
     * }
     * @Bean
     * public ArrayList<String> arrayList() {
     *     return new ArrayList<>();
     * }
     */
