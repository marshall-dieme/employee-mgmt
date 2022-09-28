package com.spring.employeeservice;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableFeignClients("com.spring.employeeservice")
public class EmployeeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }

    @Bean
    public ModelMapper getModel() {
        ModelMapper mapper = new ModelMapper();
        Converter<String, LocalDate> converter = new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date = LocalDate.parse(mappingContext.getSource(), format);
                return date;
            }
        };
        /*Converter<String, LocalDate> converter = mappingContext -> {
            //Create a format pattern
            //DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            //Convert the String to LocalDate with the format pattern
            //LocalDate date = LocalDate.parse(mappingContext.getSource(),
                   // DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            //Return the localdate
            return LocalDate.parse(mappingContext.getSource(),
                     DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        };*/
        mapper.addConverter(converter);
        return mapper;
    }

}
