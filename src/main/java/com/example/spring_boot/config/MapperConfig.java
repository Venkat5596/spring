package com.example.spring_boot.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.convention.MatchingStrategies.LOOSE;


@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        //ENABLED THE nested objects
        modelMapper.getConfiguration().setMatchingStrategy(LOOSE);

        return modelMapper;
    }

    @Bean
    public OpenAPI open(){
        return new OpenAPI()
                .info(new Info().title("Open API")
                .description("hii this is me")
                        .version("1.0.0"));


    }

}
