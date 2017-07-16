package com.kpo;

import java.io.*;
import java.sql.Array;
import java.util.*;

/**
 * Keszitette: Bozsik Armand Viktor on 2017.04.06..
 * Cel: A jatek alapveto mechanizmusat biztosito osztaly
 */
class Mechanizmus extends Jatekter {                                                                                    // A jatekter osztalyban levo infok fontosak, orokoltessunk onnan (csak a csomagban lathato az osztaly)
    private final Map JatekosEsOsszesPontszam = new HashMap();                                                          // Nev es osszes pont Mapben tarolasa
    private String nev;                                                                                                 // Jatekos neve (Statisztikahoz!)
    private final Jatekos jatekos;                                                                                      // Jatekosnak valtozo
    private final Ellenfel ellenfel;                                                                                    // es az ellenfelet (aki itt most a gep! TODO: nyaron multiplayer!)
    private String jatekosMitValasztott;                                                                                // Mit valasztottunk
    private String ellenfelMiValasztott;                                                                                // Ellenfel mit valasztott
    private String kimenetelek;                                                                                         // Mik lehetnek az eredmenyek
    int gyozelmek;                                                                                                      // Hanyszor gyoztunk (Statisztikahoz!)
    int veresegek;                                                                                                      // Hanyszor vesztettunk (Statisztikahoz!)
    int dontetlenek;                                                                                                    // Hanyszor lett dontetlen (Statisztikahoz!)
    final String fajlnev = "statisztika.txt";                                                                           // A statisztika fajl nevet tartalmazo valtozo
    private final List beolvasottStatisztika = new ArrayList();                                                         // Beolvasott statisztikahoz tomb
    int[] PluszStat = new int[3];                                                                                       // Atlag, Szoras, Szorasnegyzet csak fajlba
                                                                                                                        //
    protected Mechanizmus() {                                                                                           // Parameter nelkuli konstruktor, protected, mert nem akartam mindenkinek elerhetove tenni az osztalyon vagy a csomagon belul lehet kell meg egyszer kesobb
        super();                                                                                                        // Osre valo hivatkozas
        jatekos = new Jatekos();                                                                                        // Hozzuk letre a jatekosunk
        ellenfel = new Ellenfel();                                                                                      // Hozzuk letre a gepi ellenfelet
    }                                                                                                                   //
                                                                                                                        //
    public void Jatsszunk() {                                                                                           // Publikus, hatha kell kesobb csomagon kivul is
        jatekosMitValasztott = jatekos.mitValasztunk();                                                                 // Mit valasztunk a jatekosunkkal
        ellenfelMiValasztott = ellenfel.mitValasztunk();                                                                // Az ellenfel mit valaszt
        kimenetelek = Eredmeny();                                                                                       // Mi lesz a vegeredmeny
        kimenetelMegtekintese();                                                                                        // Vegeredmeny kiirasa
        Statisztika();                                                                                                  // Meccsek statisztikaja
        statisztikaMegtekintese();                                                                                      // Statisztika kiiratasa
        StatisztikaFajlbaIrasa();                                                                                       // Statisztika fajlbairasa az adott jatek adataival
        ElozoJatekStatisztikajanakBetoltese();                                                                          //
        System.out.println("==========================================");                                               //
        ElozoJatekStatisztikajanakKiirasa();                                                                            //
        System.out.println("==========================================");                                               //
    }                                                                                                                   //
                                                                                                                        //
    public final String Eredmeny() {                                                                                    // String visszateresi erteku fuggveny
        if (jatekosMitValasztott.equals(ellenfelMiValasztott)) {                                                        // Ha a ket jatekos valsztasa egyezik
            return Dontetlen;                                                                                           // akkor dontetlen
        }                                                                                                               //
                                                                                                                        //
        if (jatekosMitValasztott.equals("Ko")) {                                                                        // Ha a jatekos kovet valaszt (logikai egyezes szerint nezem)
            return (ellenfelMiValasztott == Ollo ? Gyozelem : Vereseg);                                                 // es az ellenfel ollot, akkor gyozelem, egyebkent vereseg
        }                                                                                                               //
        else if (jatekosMitValasztott.equals("Papir")) {                                                                // Ha a jatekos papirt valaszt
            return (ellenfelMiValasztott == Ko ? Gyozelem : Vereseg);                                                   // es az ellenfel kovet, akkor gyozelem, egyebkent vereseg
        }                                                                                                               //
        else if (jatekosMitValasztott.equals("Ollo")) {                                                                 // Ha a jatekos ollot valaszt
            return (ellenfelMiValasztott == Papir ? Gyozelem : Vereseg);                                                // es az ellenfel papirt, akkor gyozelem, egyebkent vereseg
        }                                                                                                               //
        return Vereseg;                                                                                                 // Ha ezek nem teljesulnenek akkor vereseg
    }                                                                                                                   //
                                                                                                                        //
    private final void kimenetelMegtekintese() {                                                                        // privat, csak osztalyon belul hasznalom
        if (kimenetelek.equals("Gyozelem")) {                                                                           // Ha az eredmeny gyozelem
            System.out.println("A " + jatekosMitValasztott + " erosebb mint a" + ellenfelMiValasztott);                 // akkor kiiratunk
        }                                                                                                               //
        else if (kimenetelek.equals("Dontetlen")) {                                                                     // Ha az eredmeny dontetlen
            System.out.println("A " + jatekosMitValasztott + " es a " + ellenfelMiValasztott +                          // akkor kiiratunk
                               " semlegesek egymasra nezve.");                                                          //
        }                                                                                                               //
        else if (kimenetelek.equals("Vereseg")) {                                                                       // Ha az eredmeny vereseg
            System.out.println("A " + jatekosMitValasztott + " gyengebb mint a " + ellenfelMiValasztott);               // akkor kiiratunk
        }                                                                                                               //
    }                                                                                                                   //
                                                                                                                        //
    public final void Statisztika() {                                                                                   // Statisztika
        if (kimenetelek == Gyozelem) {                                                                                  // Gyozelmek vizsgalata
            gyozelmek++;                                                                                                // es feljegyzese
        }                                                                                                               //
        else if (kimenetelek == Dontetlen) {                                                                            // Dontetlenek vizsgalata
            dontetlenek++;                                                                                              // es feljegyzese
        } else {                                                                                                        // Veresegek vizsgalata
            veresegek++;                                                                                                // es feljegyzese
        }                                                                                                               //
    }                                                                                                                   //
                                                                                                                        //
    private final void statisztikaMegtekintese() {                                                                      // Statisztika kiiratasa
        System.out.println("Osszjatekok szama: " + (gyozelmek+dontetlenek+veresegek));                                  // Osszes jatek melybol
        System.out.println("Nyereseid szama: " + gyozelmek);                                                            // gyozelem
        System.out.println("Dontetlenek szama: " + dontetlenek);                                                        // dontetlen
        System.out.println("Veresegek szama: " + veresegek);                                                            // vereseg
    }                                                                                                                   //
                                                                                                                        //
    private final void StatisztikaFajlbaIrasa() {                                                                       //
        try (BufferedWriter StatisztikaFajlbaIrasa = new BufferedWriter(new FileWriter(fajlnev))) {                     // Buffered es FileWriter
            StatisztikaFajlbaIrasa.write("Osszjatekok szama: " +                                                    //
                                            System.lineSeparator() + (gyozelmek+dontetlenek+veresegek) +                //
                                            System.lineSeparator() +                                                    // Osszjatekok es platformfuggetlen sorveg
                                            "Nyereseid szama: " +                                                       //
                                            System.lineSeparator() + gyozelmek +                                        //
                                            System.lineSeparator() +                                                    // Gyozelmek
                                            "Dontetlenek szama: " +                                                     //
                                            System.lineSeparator() + dontetlenek +                                      //
                                            System.lineSeparator() +                                                    // Dontetlenek
                                            "Veresegek szama: " +                                                       //
                                            System.lineSeparator() + veresegek +                                        //
                                            System.lineSeparator());                                                    // Veresegek
                                                                                                                        //
        }                                                                                                               //
        catch (IOException e) {                                                                                         //
            System.out.println("A fajl nem hozhato lette!");                                                            //
            System.out.println("A reszleteket lasd alabb:");                                                            //
            e.printStackTrace();                                                                                        //
        }                                                                                                               //
    }                                                                                                                   //
                                                                                                                        //
    private final void ElozoJatekStatisztikajanakBetoltese() {                                                          //
        try (BufferedReader StatisztikaBeolvasasa = new BufferedReader(new FileReader(fajlnev))) {                      // Fajlba
            String sor;                                                                                                 // Sor
            while ((sor = StatisztikaBeolvasasa.readLine()) != null) {                                                  // Soronkent
                beolvasottStatisztika.add(sor);                                                                         // Listaba
            }                                                                                                           //
        }                                                                                                               //
        catch (IOException e) {                                                                                         //
            System.out.println("A fajl nem olvashato!");                                                                //
            System.out.println("A reszleteket lasd alabb: ");                                                           //
            e.printStackTrace();                                                                                        //
        }                                                                                                               //
    }                                                                                                                   //
                                                                                                                        //
    private final void ElozoJatekStatisztikajanakKiirasa() {                                                            //
        System.out.println("A fajlban igy szerepel: ");                                                                 //
        beolvasottStatisztika.forEach(System.out::println);                                                             // Beolvasott ertekek kiiratasa
    }
}
