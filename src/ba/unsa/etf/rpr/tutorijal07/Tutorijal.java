package ba.unsa.etf.rpr.tutorijal07;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import static java.util.Locale.US;

public class Tutorijal {

    public static void main(String[] args) {
        ArrayList gradovi = ucitajGradove();
    }
    public static ArrayList<Grad> ucitajGradove(){
        Scanner ulaz;
        ArrayList gradoviLista = new ArrayList<Grad>();
        try {
            ulaz = new Scanner(new FileReader("mjerenja.txt"));
            ulaz.useLocale(US);
            ulaz.useDelimiter(",|\\n");
            while(ulaz.hasNext()){
                //System.out.println("NOVI");
                String nazivGrada = ulaz.next();
                double temperature[] = new double[1000];
                System.out.println(nazivGrada);
                int brojac = 0;
                while(ulaz.hasNextDouble()){
                    //System.out.println("TEMPERATURE");
                    temperature[brojac] = ulaz.nextDouble();
                    System.out.println(temperature[brojac]);
                    brojac++;
                    if(brojac == 1000) break;
                }
                Grad grad = new Grad();
                grad.setBrojStanovnika(0);
                grad.setNaziv(nazivGrada);
                grad.setTemperature(temperature);
                gradoviLista.add(grad);
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Datoteka mjerenja.txt ne postoji ili se ne može otvoriti!");
        }
        return gradoviLista;
    }
    public static UN ucitajXml(ArrayList<Grad> gradovi){
        UN un = new UN();

        return un;
    }
    public static void zapisiXml(UN un){

    }
}
