//package com.booking.hotelservice.converter;
//
//import aj.org.objectweb.asm.TypeReference;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//import java.io.IOException;
//import java.util.List;
//
//@Converter
//public class StringListConverter implements AttributeConverter<List<String>, String> {
//
//  private static final ObjectMapper objectMapper = new ObjectMapper();
//
//  @Override
//  public String convertToDatabaseColumn(List<String> attribute) {
//    try {
//      return objectMapper.writeValueAsString(attribute);
//    } catch (JsonProcessingException e) {
//      throw new RuntimeException(e);
//    }
//  }
//
//  @Override
//  public List<String> convertToEntityAttribute(String dbData) {
//    try {
//      return objectMapper.readValue(dbData, new TypeReference<List<String>>() {});
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//  }
//}
//
