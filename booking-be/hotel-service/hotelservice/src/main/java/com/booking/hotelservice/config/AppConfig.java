package com.booking.hotelservice.config;

import com.cloudinary.Cloudinary;
import java.util.HashMap;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
    return modelMapper;
  }

  @Bean
  public Cloudinary getCloudinary(){
    Map config = new HashMap();
    config.put("cloud_name", "dbkgbh9kl");
    config.put("api_key", "847943874193696");
    config.put("api_secret", "Yj5APAWn75a7Jz6EmG9pCNuLUPM");
    config.put("secure", true);
    return new Cloudinary(config);
  }
}
