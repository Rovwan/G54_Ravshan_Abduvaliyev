package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ovqat_b {
    private final String id= UUID.randomUUID().toString();
    private String ovqatid;
    private String ovqat_name;
}
