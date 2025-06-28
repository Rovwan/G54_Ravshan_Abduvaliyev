package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import org.example.db.Buyurtma;
import org.example.db.Ovqat;
import org.example.entity.Afitsiyant;
import org.example.entity.Buyurtma_olish;
import org.example.entity.Ovqat_b;
import org.example.entity.Taom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.*;

import static org.example.db.Buyurtma.MIJOZ_OVQATLANYAPTI;
import static org.example.db.Datasource.*;
import static org.example.db.Scaner.*;

public class OrderService {
    static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    static String fileName = "C:\\G54_JavaPDP\\5-modul.Imtihon/src/main/resources/Afitsiyant.json";
    static String outputFile1 = "C:\\G54_JavaPDP\\5-modul.Imtihon/src/main/resources/Buyurtma_olish.json";
    static String outputFile2 = "C:\\G54_JavaPDP\\5-modul.Imtihon/src/main/resources/Taom.json";

    public void service(){
        while (true){
            readafitsiyant();
            readbuyurtma();
            readtaom();
            System.out.println("""
                    0 exit
                    1 add taom
                    2 add afitsiyant
                    3 buyurtma 
                    4 afitsiyant statistikasi
                    """);
            switch (intscanner.nextInt()){
                case 0->{
                    return;
                }
                case 1->{
                    addtaom();
                }
                case 2->{
                    addafitsiyant();
                }
                case 3->{
                    buyurtmaolish();
                }
            }
        }
    }

    private void buyurtmaolish() {

        while (true)
        {
            System.out.println("""
                    0 exit
                    1 buyurtma qabul qilish
                    2 qabul qilinga buyurtmani yana ovqat qo'shish yoki tugatish 
                    3 show buyurtma
                    """);
            switch (intscanner.nextInt()){
                case 0->{
                    return;
                }
                case 1->{
                    Buyurtma_olish buyurtmaOlish=new Buyurtma_olish();
                    System.out.println("enter stol raqami:");
                    buyurtmaOlish.setStol_raqami(scanner.nextLine());


                    afitsiyants.forEach(company ->
                            System.out.println("ID: " + company.getId() + ", Name: " + company.getName())
                    );

                    System.out.print("afitsiyant id ni tanlang: ");
                    String id =scanner.nextLine();
                    boolean found = false;
                    for (Afitsiyant afitsiyant : afitsiyants) {
                        if(Objects.equals(id,afitsiyant.getId())){
                            found = true;
                            buyurtmaOlish.setAfitsiyantid(id);
                        }
                    }

                    if (!found) {
                        System.out.println("id noto‘g‘ri.");
                    }

                    Ovqat res = null;

                    while (res == null) {
                        System.out.println("""
                1 ICHIMLIK
                2 MILLIY_TAOMLAR
                3 SALAT
                """);
                        switch (scanner.nextInt()) {
                            case 1 ->
                            {
                                res = Ovqat.ICHIMLIK;
                            }
                            case 2 ->
                            {
                                res = Ovqat.MILLIY_TAOMLAR;
                            }
                            case 3->{
                                res = Ovqat.SALAT;
                            }
                            default -> System.out.println("Noto‘g‘ri tanlov qaytadan urinib ko‘ring");
                        }
                    }
                    scanner.nextLine();
                    System.out.println("Quyidagi " + res + " turdagi ovqatlar mavjud:");
                    boolean mavjudX = false;
                    for (Taom taom : taoms) {
                        if(Objects.equals(taom.getOvqat(),res)){
                            System.out.println("<<-- "+taom+" -->>");
                            mavjudX = true;
                        }
                    }
                    if (!mavjudX) {
                        System.out.println("<<-- bu turdagi ovqat tugab qolgan -->>");
                        return;
                    }

                    System.out.println("enter taom id:");
                    String id1=scanner.nextLine();
                    for (Taom taom : taoms) {
                        if(Objects.equals(id1,taom.getId())){
                            Ovqat_b ovqatB=new Ovqat_b();
                            ovqatB.setOvqatid(id1);
                            ovqatB.setOvqat_name(taom.getName());
                            List<Ovqat_b> inputProductList = new ArrayList<>();
                            inputProductList.add(ovqatB);
                            buyurtmaOlish.setOvqat_bs(inputProductList);
                        }
                    }






                    buyurtmaOlish.setBuyurtma(MIJOZ_OVQATLANYAPTI);
                    String q= LocalDate.now().toString();
                    buyurtmaOlish.setDate(q);
                    buyurtmaOlishes.add(buyurtmaOlish);
                    System.out.println("sucssesfull buyurtma");
                    writebuyurtma();

                }
                case 2->{
                    while (true){
                        System.out.println();
                    }
//                    for (Buyurtma_olish buyurtmaOlish : buyurtmaOlishes) {
//                        if(Objects.equals(buyurtmaOlish.getBuyurtma(),MIJOZ_OVQATLANYAPTI)){
//                            System.out.println("<< "+buyurtmaOlish+" >>");
//                        }
//                    }
//                    System.out.println("enter stol raqamingizni kiriting:");
//                    String raqam=scanner.nextLine();
//                    for (Buyurtma_olish buyurtmaOlish : buyurtmaOlishes) {
//                        if(Objects.equals(buyurtmaOlish.getBuyurtma(),MIJOZ_OVQATLANYAPTI) && Objects.equals(buyurtmaOlish.getStol_raqami(),raqam)){
//
//                        }
//                    }
                }
                case 3->{
                    for (Buyurtma_olish buyurtmaOlish : buyurtmaOlishes) {
                        System.out.println("<< "+buyurtmaOlish+" >>");
                    }
                }
            }
        }
    }

    private void addafitsiyant() {
        Afitsiyant a=new Afitsiyant();
        System.out.println("enter name:");
        a.setName(scanner.nextLine());
        System.out.println("enter age:");
        a.setAge(intscanner.nextInt());
        System.out.println("sucssusfull add afitsiyant");
        afitsiyants.add(a);
        writeafitsiyant();

    }

    private void addtaom() {

        Taom taom=new Taom();
        System.out.println("enter taom name:");
        taom.setName(scanner.nextLine());
        System.out.println("enter price:");
        taom.setPrice(doublescanner.nextDouble());

        System.out.println("Ovaqt turini tanlang:");
        System.out.println("1. Ichimlik");
        System.out.println("2. milliy taom(yiydigan)");
        System.out.println("3. salat");

        int holatTanlov = intscanner.nextInt();
        switch (holatTanlov) {
            case 1:
                taom.setOvqat(Ovqat.ICHIMLIK);
                break;
            case 2:
                taom.setOvqat(Ovqat.MILLIY_TAOMLAR);
                break;
                case 3:
                taom.setOvqat(Ovqat.SALAT);
                break;
            default:
                System.out.println("Noto'g'ri tanlov, default holat MILLIY_TAOMLAR o'rnatildi");
                taom.setOvqat(Ovqat.MILLIY_TAOMLAR);
        }

        System.out.println("sucssusfull add taom");
        taoms.add(taom);
        writetaom();

    }



        @SneakyThrows
    public static void writeafitsiyant() {
        BufferedWriter writer =new BufferedWriter(new FileWriter(fileName));
        writer.write(gson.toJson(afitsiyants));
        writer.close();
    }

    @SneakyThrows
    public static void readafitsiyant(){
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String s = "";
        StringBuilder sb = new StringBuilder("");
        while ((s = reader.readLine()) !=null){
            sb.append(s).append("\n");
        }

        reader.close();

        Afitsiyant[] userArray = gson.fromJson(sb.toString(), Afitsiyant[].class);
        afitsiyants=new ArrayList<>(Arrays.asList(userArray));

    }

    @SneakyThrows
    public static void writebuyurtma() {
        BufferedWriter writer =new BufferedWriter(new FileWriter(outputFile1));
        writer.write(gson.toJson(buyurtmaOlishes));
        writer.close();
    }

    @SneakyThrows
    public static void readbuyurtma(){
        BufferedReader reader = new BufferedReader(new FileReader(outputFile1));
        String s = "";
        StringBuilder sb = new StringBuilder("");
        while ((s = reader.readLine()) !=null){
            sb.append(s).append("\n");
        }

        reader.close();

        Buyurtma_olish[] userArray = gson.fromJson(sb.toString(), Buyurtma_olish[].class);
        buyurtmaOlishes=new ArrayList<>(Arrays.asList(userArray));

    }

    @SneakyThrows
    public static void writetaom() {
        BufferedWriter writer =new BufferedWriter(new FileWriter(outputFile2));
        writer.write(gson.toJson(taoms));
        writer.close();
    }

    @SneakyThrows
    public static void readtaom(){
        BufferedReader reader = new BufferedReader(new FileReader(outputFile2));
        String s = "";
        StringBuilder sb = new StringBuilder("");
        while ((s = reader.readLine()) !=null){
            sb.append(s).append("\n");
        }

        reader.close();

        Taom[] userArray = gson.fromJson(sb.toString(), Taom[].class);
        taoms=new ArrayList<>(Arrays.asList(userArray));

    }
}
