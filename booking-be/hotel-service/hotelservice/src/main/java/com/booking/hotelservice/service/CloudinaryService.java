package com.booking.hotelservice.service;

import com.cloudinary.Cloudinary;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class CloudinaryService {

  private final Cloudinary cloudinary;

  public Map<String, Object> upload(MultipartFile file) {
    try {
      return cloudinary.uploader().upload(
          file.getBytes(),
          Map.of("folder", "booking")
      );
    } catch (IOException io) {
      log.error("Error uploading image to Cloudinary", io);
      throw new RuntimeException("Image upload fail");
    }
  }
}
