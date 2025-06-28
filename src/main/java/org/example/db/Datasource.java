package org.example.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.entity.Afitsiyant;
import org.example.entity.Buyurtma_olish;
import org.example.entity.Taom;

import java.util.ArrayList;
import java.util.List;

public class Datasource {

    public static List<Afitsiyant> afitsiyants=new ArrayList<>();
    public static List<Buyurtma_olish> buyurtmaOlishes=new ArrayList<>();
    public static List<Taom> taoms=new ArrayList<>();


//    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static {

    }
}
