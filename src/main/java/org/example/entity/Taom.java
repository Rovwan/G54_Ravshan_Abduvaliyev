package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.db.Ovqat;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taom {
    private final String id= UUID.randomUUID().toString();
    private String name;
    private double price;
    private Ovqat ovqat;
}
