package com.kpo;

import java.util.Scanner;
import com.kopapirhibakezeles.FelhasznaloiInputHiba;

public class Main {
    public static void main(String[] args) {
        Scanner JatekVege = new Scanner(System.in);                                                                     // Billentyuzetrol olvasom hogy vege-e a jateknak
        Scanner Nev = new Scanner(System.in);                                                                           // Beolvasom a jatekos nevet
                                                                                                                        //
        System.out.println("=========================================");                                                //
        System.out.println("Programozas I. beadando");                                                                  //
        System.out.println("Bozsik Armand Viktor");                                                                     //
        System.out.println("Szegedi Tudomanyegyetem");                                                                  //
        System.out.println("Programtervezo informatikus (levelezo)");                                                   //
        System.out.println("=========================================");                                                //
        System.out.println("\n");                                                                                       //
        System.out.println("Add meg a teljes neved:");                                                                  //
        String JatekosNev = Nev.nextLine();                                                                             // Itt olvasom a jatekos nevet
                                                                                                                        //
        try {                                                                                                           //
            HibasNevFormatum(JatekosNev);                                                                               // Hibakezeles
        }                                                                                                               //
        catch (Exception e) {                                                                                           //
            System.out.println("Hiba tortent!");                                                                        //
        }                                                                                                               //
                                                                                                                        //
        String[] CsaladnevUtonev = JatekosNev.split(" ");                                                         // Nev tombbe szokoz menten
        boolean TeljesNev = MegadtaATeljesnevet(CsaladnevUtonev);                                                       // Ellenorzom hogy helyes formatumu-e
                                                                                                                        //
        if (TeljesNev == true) {                                                                                        // Ha igen, akkor kiiratok
            System.out.println("Megadtad a teljes neved igy kezdodhet a jatek\n");                                      //
        } else {                                                                                                        // Ha nem, akkor is es leallok
            System.out.println("Sajnos nem adtad meg a teljes neved igy ki kell hogy lepjek.");                         //
            System.exit(1);                                                                                      //
        }                                                                                                               //
                                                                                                                        //
        System.out.println("Szia " + JatekosNev + "!");                                                                 //
        System.out.println("\n");                                                                                       //
        System.out.println("A jatek kezdesehez valassz a (k)o, " +                                                      //
                           "(p)apir, (o)llo lehetosegek kozul. Ird be, es uss Entert!");                                //
        System.out.println("Uj jatekhoz a jatek vegen uss le egy billentyut a V kivetelevel (mert arra kilep)");        //
        System.out.println("Utana a fenti opciokbol valassz ujbol!");                                                   //
                                                                                                                        //
        Mechanizmus jatek = new Mechanizmus();                                                                          // Meghivom az azonos nevu konstruktort a fajlbol
        char vege = ' ';                                                                                                // A jatek veget jelzo karakter ha ez a megfelelo ertket kapja a jatek leall.
                                                                                                                        //
        while (vege != 'V') {                                                                                           // Itt meg is szabom a feltetelt
            jatek.Jatsszunk();                                                                                          // Meghivom a jatekmechanizmust biztosito fajlbol a metodust amely a jatek magjat kepezi
                                                                                                                        //
            System.out.println("Uj jatek kezdesehez nyomj meg egy billentyut. Ha ki akarsz lepni akkor a V-t.");        // Uzenet a jatek vegen.
                                                                                                                        //
            vege = JatekVege.                                                                                           // Beepitett fuggvenyekkel
                    nextLine().                                                                                         // vizsgalom a valtozo
                    toUpperCase().                                                                                      // ertekenek elso
                    charAt(0);                                                                                          // karakteret
        }                                                                                                               //
    }                                                                                                                   //
    private static boolean MegadtaATeljesnevet(String CsaladnevUtonev[]) {                                              // A fenti tombot kapja parameterkent
        int i = 0;                                                                                                      //
        while (i < CsaladnevUtonev.length) {                                                                            // Vegigmegyek rajta
            if (CsaladnevUtonev[i].trim().equals("")) {                                                                 // Megnezem, hogy az elem nem-e ures
                CsaladnevUtonev[i] = null;                                                                              // Ha igen, akkor nullra allitom
            }                                                                                                           //
            i++;                                                                                                        //
        }                                                                                                               //
        int HanyElem = 0;                                                                                               // Megnezem hany darabbol all a String
                                                                                                                        //
        for (int j = 0; j < CsaladnevUtonev.length; j++) {                                                              // Vegigmegyek a tombon
            if (CsaladnevUtonev[j] != null) {                                                                           // Ha nem null
                HanyElem++;                                                                                             // akkor megnovelem a valtozo erteket
            }                                                                                                           //
        }                                                                                                               //
        return HanyElem >= 2;                                                                                           // Ha az elemszam ketto vagy annal nagyobb, akkor visszaadom az eredmenyt
    }                                                                                                                   //
                                                                                                                        //
    private static void HibasNevFormatum(String Nev) throws FelhasznaloiInputHiba {                                     // Input ellenorzese
        if (Character.isDigit(Nev.charAt(0))) {                                                                         // Eloszor startswith segitsegevel akartam es regexszel, de ez talan jobban olvashato
            System.out.println("Nem kezdodhet szammal a neved!");                                                       // Kiiratok
        }                                                                                                               //
        else {                                                                                                          //
            System.out.println("A neved megfelelo formatumu!");                                                         // Kiiratok
        }
    }
}
