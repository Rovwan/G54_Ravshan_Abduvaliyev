package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.db.Buyurtma;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Buyurtma_olish {
    private final String id= UUID.randomUUID().toString();
    private String stol_raqami;
    private String afitsiyantid;
    private Buyurtma buyurtma;
    private String date;
    private List<Ovqat_b> ovqat_bs=new ArrayList<>();
}
