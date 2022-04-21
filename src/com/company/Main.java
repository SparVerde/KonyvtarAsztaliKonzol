package com.company;
// bemásol mysql-connector + jobb kloik add as library
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try {
            Statisztika db = new Statisztika();
            int i =0;
            int j =0;
            int eq;
            int darab =0;
            int k =0;
            int max =0;
            int max2 =0;
            int kolcsonzesszama =0;
            String title="";
            String szerzo="";
            int kiadas = 0;
            int oldal=0;
            String legtobbetSzerzo="";
            String konyv="Quo Animi Quia Eveniet Aut";
            String van="";

            ArrayList<Konyv> lista = ((Statisztika) db).ListaFeltolt();
            ArrayList<Kolcsonzes> listakolcsonzes = ((Statisztika) db).ListaFeltolt2();
            ArrayList<String> authors = new ArrayList<>();
            ArrayList<Integer> authorsdb = new ArrayList<>();
            for (Konyv e : lista) {
                //System.out.println(e.getAuthor());
                if(!authors.contains(e.getAuthor())){authors.add(e.getAuthor());}
                i++;
                if (e.getPage_count()>500){j++;}
                if (e.getPage_count()>max){max =e.getPage_count(); title = e.getTitle();
                    szerzo = e.getAuthor(); kiadas = e.getPublish_year(); oldal = e.getPage_count();}
                if(e.getPublish_year()<1950){;k++;if(k>0){van = "Van ilyen könyv";}} else{van = "Nincs ilyen könyv";}
                if(e.getTitle().equals(konyv)){;eq=e.getId();
                    for (Kolcsonzes v:listakolcsonzes) {if (eq ==v.getBook_id()){kolcsonzesszama++;}}
                }
            }
            for (String a : authors) {
                for (Konyv e : lista) {
                    if(e.getAuthor().equals(a)){darab++;}
                }
                authorsdb.add(darab);
                darab =0;
                //System.out.println(a);
            }
            for (int z : authorsdb) {
                if (z>max2){max2 =z;}
                legtobbetSzerzo = authors.get(z);
            }
            System.out.print("Adjon meg egy könyv címet:");
            String konyv2 = sc.nextLine();
            System.out.println("500 oldalnál hosszabb könyvek száma: "+j+"\nA leghosszabb könyv: \n\t" +
                    "Szerző: "+szerzo+"\n\tCím: "+title+"\n\tKiadás éve: "+kiadas+"\n\tOldalszám: "+oldal+"\n"
            +"A legtöbb könyvvel rendelkező szerző: "+legtobbetSzerzo+"\nAdjon meg egy könyv címet: "+konyv2+". A(z) megadott könyv "+kolcsonzesszama+"x lett kikölcsönözve");
/*
            PeldaRekord r = new PeldaRekord(0, "Kovács József", Date.valueOf("1800-01-01"), 11, "Zöld");
            db.rekordHozzaadasa(r);
            db.rekordTorlese(6);
            lista.clear();
            lista = db.ListaFeltolt();
            for (CegadatRekord e : lista) {
                System.out.println(e);
            }
*/

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
