package com.kpo;

import java.util.Scanner;

/**
 * Keszitette: Bozsik Armand Viktor on 2017.04.06..
 * Cel: A sajat jatekosunk viselkedesenek leirasa
 */
public class Jatekos extends Jatekter {
    Scanner bemenet;                                                                                                    // Billentyuzetrol valo olvasas objektuma
                                                                                                                        //
    public Jatekos() {                                                                                                  // Jatekos
        super();                                                                                                        // Os
        bemenet = new Scanner(System.in);                                                                               // Alapertelmezett bementrol olvasa
    }                                                                                                                   //
                                                                                                                        //
    public final String mitValasztunk() {                                                                               // Mit valasztunk
        final char jatekosMitValasztott = bemenet.                                                                      // A bemenet
                                    nextLine().                                                                         // elso
                                    toUpperCase().                                                                      // karakterenek
                                    charAt(0);                                                                          // vizsgalata
                                                                                                                        //
        if (jatekosMitValasztott == 'K')                                                                                // K billentyure
        {                                                                                                               //
            return Ko;                                                                                                  // kovet valasztunk
        }                                                                                                               //
        else if (jatekosMitValasztott == 'P')                                                                           // P billentyure
        {                                                                                                               //
            return Papir;                                                                                               // papirt valasztunk
        }                                                                                                               //
        else if (jatekosMitValasztott == 'O')                                                                           // O billentyure
        {                                                                                                               //
            return Ollo;                                                                                                // ollot valasztunk
        }                                                                                                               //
        System.out.println("Hiba!");                                                                                    // Ha nem ezeket valasztjuk akkor hiba van
        return mitValasztunk();                                                                                         // Jatek vegen rekurzio, hogy uj jatekba tudjunk kezdeni
    }
}
