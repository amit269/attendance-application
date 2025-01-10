//package com.developer.AttendanceApp.Converter;
//
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//import java.time.Duration;
//
//@Converter(autoApply = true)
//public class DurationConverter implements AttributeConverter<Duration, Long> {
//
//    @Override
//    public Long convertToDatabaseColumn(Duration attribute) {
//        return (attribute == null) ? null : attribute.toMinutes(); // Or use `toSeconds()` depending on your requirement
//    }
//
//    @Override
//    public Duration convertToEntityAttribute(Long dbData) {
//        return (dbData == null) ? null : Duration.ofMinutes(dbData); // Or use `Duration.ofSeconds(dbData)` based on what you stored
//    }
//}
