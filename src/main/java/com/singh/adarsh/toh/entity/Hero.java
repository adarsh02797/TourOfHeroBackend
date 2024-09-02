package com.singh.adarsh.toh.entity;

import com.singh.adarsh.toh.service.SequenceGeneratorService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "heroes")
@AllArgsConstructor
@NoArgsConstructor
public class Hero {
    @Transient
    public static final String SEQUENCE_NAME = "hero_sequence";

    @Id
    private int id;
    @NonNull
    private String name;

}
