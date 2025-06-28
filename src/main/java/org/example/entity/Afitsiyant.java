package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Afitsiyant {
    private final String id= UUID.randomUUID().toString();
    private String name;
    private int age;
}
