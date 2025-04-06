package com.youtube.central.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public final String exchangeName = "notification-exchange";
    public final String queueName = "notification-queue";

    public final String routingKey = "notification-123";

    @Bean
    public DirectExchange getDirectExchange(){
        DirectExchange exchange = new DirectExchange(exchangeName);
        return exchange;
    }

    @Bean
    public Queue getMessagingQueue(){
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public CachingConnectionFactory getConnectionFactory(){
        // ConnectionFactory is nothing but connection deatils for rabbbitmq server
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate getRabbitTemplate(CachingConnectionFactory connectionFactory){
        // JSON -> Class Objects
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Binding bindQueueWithExchange(DirectExchange exchange, Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
}

   
    

 /*
    NOTES:
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
