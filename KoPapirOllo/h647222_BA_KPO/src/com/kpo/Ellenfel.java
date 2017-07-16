package com.kpo;

import java.util.Random;

/**
 * Keszitette Bozsik Armand Viktor on 2017.04.06..
 * Cel: A gepi ellenfel viselkedesenek meghatarozasa
 */
public final class Ellenfel extends Jatekter {                                                                          // Gepi ellenfel
    private final Random r;                                                                                             // Veletlenszam tipusu valtozo generalasa
                                                                                                                        //
    public Ellenfel() {                                                                                                 // Ellenfel
        super();                                                                                                        // Os
        r = new Random();                                                                                               // veletlenszam objektum generalasa
    }                                                                                                                   //
                                                                                                                        //
    public final String mitValasztunk() {                                                                               // Valasztas a veletlenszam alapjan
        final int ellenfelMitValasztott = 1 + r.nextInt(3);                                                      // 3-mal a [0;2] zart intervallumon generalok, de az nem jo, ezert egyet hozza kell hogy adjak
                                                                                                                        //
        if (ellenfelMitValasztott == 1)                                                                                 // Ha a veletlenszam 1
        {                                                                                                               //
            return Ko;                                                                                                  // akkor kovet valasztott
        }                                                                                                               //
        else if (ellenfelMitValasztott == 2)                                                                            // Ha a veletlenszam 2
        {                                                                                                               //
            return Papir;                                                                                               // akkor papirt valasztott
        }                                                                                                               //
        return Ollo;                                                                                                    // Ha a veletlenszam 3
    }                                                                                                                   // akkor ollo
}
