package ba.unsa.etf.rpr.tutorijal07;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import static java.util.Locale.US;

public class Tutorijal {

    public static void main(String[] args) {
        //ArrayList gradovi = ucitajGradove();
        UN un = new UN();
        Grad g1 = new Grad("Sarajevo", 300000);
        Grad g2 = new Grad("Zagreb", 500000);
        Grad g3 = new Grad("Beograd", 900000);
        Drzava drzava1 = new Drzava("BiH", 3500000, 50000,"km^2", g1 );
        Drzava drzava2 = new Drzava("Hrvatska", 5000000,70000,"km^2",g2);
        Drzava drzava3 = new Drzava("Srbija",6000000,80000,"km^2",g3);
        ArrayList<Drzava> drzave = new ArrayList<Drzava>();
        drzave.add(drzava1);
        drzave.add(drzava2);
        drzave.add(drzava3);
        un.setDrzave(drzave);
        //zapisiXml(un);
        UN unNovi = ucitajXml();

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
    public static UN ucitajXml(){
        UN un = new UN();
        XMLDecoder ulaz = null;
        try {
            ulaz = new XMLDecoder(new FileInputStream("UN.xml"));
            un = (UN) ulaz.readObject();
            ulaz.close();
        }catch (Exception e){
            System.out.println("Greska: " + e );
        }
        /*for(Drzava d: un.getDrzave()){
            System.out.println(d);
        }*/
        return un;
    }
    public static void zapisiXml(UN un){
        XMLEncoder izlaz = null;
        try {
            izlaz = new XMLEncoder(new FileOutputStream("UN.xml"));
            izlaz.writeObject(un);
            izlaz.close();
        } catch (Exception e) {
            System.out.println("Greska: " + e );
        }
    }
}
