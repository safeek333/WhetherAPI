package com.wether;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Configuration
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
        //System.out.println( "Hello World!" );
    }
    
    @Bean
    public RestTemplate getBean(){
    	return new RestTemplate();
    }
    
    @Bean
    public CacheLoader<String, String> getCacheLoader(){
    	return new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return key.toUpperCase();
            }
        };
    }
    
    @Bean
    public LoadingCache<String, String> getLoadingCache(CacheLoader<String, String> loader){
        return CacheBuilder.newBuilder().expireAfterWrite(2,TimeUnit.HOURS).build(loader);
    }
}
