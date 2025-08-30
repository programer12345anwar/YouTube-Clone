package com.youtube.video_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.youtube.video_service.util.ApiTemplate;
import com.youtube.video_service.util.ApiTemplateImpl;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;

@org.springframework.context.annotation.Configuration
public class AppConfig {

    @Value("${image.url.endpoint}") //@Value annotation help us to get the value of a property defined in app properties file
    String urlEndPoint;
    @Value("${image.private.key}")
    String privateKey;
    @Value("${image.public.key}")
    String publicKey;

    @Bean //@Bean → tells Spring to create and manage an object.
    public ImageKit getImageKit() throws Exception{
         ImageKit imageKit = ImageKit.getInstance();
         Configuration configuration = new Configuration(publicKey, privateKey, urlEndPoint);//this will helps to connect with imagekit server
         imageKit.setConfig(configuration);
         return imageKit;
    }
    

    @Bean
    public ModelMapper getMapper(){
        //ModelMapper is a library that helps to map one object to another (e.g., Entity → DTO, DTO → Entity).
        //Instead of writing boilerplate setters, ModelMapper copies fields automatically.
        //So with this bean, whenever you @Autowired ModelMapper, you’ll get the same object.
        return new ModelMapper();
    }

    @Bean
    public ApiTemplate getApiTemplate(){
        //you are telling Spring: “Hey, whenever someone asks for an ApiTemplate, give them an ApiTemplateImpl"
        return new ApiTemplateImpl();
    }
    

    @Bean
    public RestTemplate getRestTemplate(){
        //RestTemplate → makes API calls to other services., it is a type of synchronous communication so current thread will be blocked and will wait for the response
        return new RestTemplate();
    }
        

}
