package pl.edu.pg.eti.ksg.po.project2.rosliny;

import pl.edu.pg.eti.ksg.po.project2.Organizm;
import pl.edu.pg.eti.ksg.po.project2.Roslina;
import pl.edu.pg.eti.ksg.po.project2.Komentator;
import pl.edu.pg.eti.ksg.po.project2.Swiat;
import pl.edu.pg.eti.ksg.po.project2.Punkt;

import java.awt.*;
import java.util.Random;

public class BarszczSosnowskiego extends Roslina {
    private static final int SILA_BARSZCZ_SOSNOWSKIEGO = 10;
    private static final int INICJATYWA_BARSZCZ_SOSNOWSKIEGO = 0;

    public BarszczSosnowskiego(Swiat swiat, Punkt pozycja, int turaUrodzenia) {

        super(TypOrganizmu.BARSZCZ_SOSNOWSKIEGO, swiat, pozycja,
                turaUrodzenia, SILA_BARSZCZ_SOSNOWSKIEGO, INICJATYWA_BARSZCZ_SOSNOWSKIEGO);
        setKolor(new Color(154, 205, 50));
        setSzansaRozmnazania(0.05);
    }

    @Override
public void Akcja() {
    int pozX = getPozycja().getX();
    int pozY = getPozycja().getY();

    this.LosujPoleDowolne(getPozycja()); 

    Kierunek[] kierunki = {Kierunek.GORA, Kierunek.DOL, Kierunek.LEWO, Kierunek.PRAWO};

    for (Kierunek k : kierunki) {
        if (!CzyKierunekZablokowany(k)) {
            Punkt sprawdzanyPunkt = null;
            switch (k) {
                case GORA:  sprawdzanyPunkt = new Punkt(pozX, pozY - 1); break;
                case DOL:   sprawdzanyPunkt = new Punkt(pozX, pozY + 1); break;
                case LEWO:  sprawdzanyPunkt = new Punkt(pozX - 1, pozY); break;
                case PRAWO: sprawdzanyPunkt = new Punkt(pozX + 1, pozY); break;
            }

            if (sprawdzanyPunkt != null) {
                Organizm ofiara = getSwiat().CoZnajdujeSieNaPolu(sprawdzanyPunkt);
                if (ofiara != null && ofiara.CzyJestZwierzeciem()) {
                    Komentator.DodajKomentarz(this.TypOrganizmuToString() + " zabija w sąsiedztwie: " + ofiara.OrganizmToString());
                    getSwiat().UsunOrganizm(ofiara);
                }
            }
        }
    }

    Random rand = new Random();
    int tmpLosowanie = rand.nextInt(100);
    if (tmpLosowanie < getSzansaRozmnazania() * 100) Rozprzestrzenianie();
}

    @Override
    public String TypOrganizmuToString() {
        return "Barszcz Sosnowskiego";
    }

    @Override
    public boolean SpecjalneDzialaniePodczasAtaku(Organizm atakujacy, Organizm ofiara) {
        if (atakujacy.getSila() >= 10) {
            getSwiat().UsunOrganizm(this);
            Komentator.DodajKomentarz(atakujacy.OrganizmToString() + " zjada " + this.OrganizmToString());
            atakujacy.WykonajRuch(ofiara.getPozycja());
        }
        return true;
    }
}
