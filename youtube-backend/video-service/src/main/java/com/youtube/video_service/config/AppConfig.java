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

    @Value("${image.url.endpoint}") //@Value annotation help us to get the value of a property defied ins app properties file
    String urlEndPoint;
    @Value("${image.private.key}")
    String privateKey;
    @Value("${image.public.key}")
    String publicKey;

    @Bean
    public ImageKit getImageKit() throws Exception{
         ImageKit imageKit = ImageKit.getInstance();
         Configuration configuration = new Configuration(publicKey, privateKey, urlEndPoint);//this will helps to connect with imagekit server
         imageKit.setConfig(configuration);
         return imageKit;
    }
    

    @Bean
    public ModelMapper getMapper(){
        return new ModelMapper();
    }

    @Bean
    public ApiTemplate getApiTemplate(){
        return new ApiTemplateImpl();
    }
    

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
        

}
