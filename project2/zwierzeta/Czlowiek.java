package pl.edu.pg.eti.ksg.po.project2.zwierzeta;

import pl.edu.pg.eti.ksg.po.project2.Zwierze;
import pl.edu.pg.eti.ksg.po.project2.Organizm;
import pl.edu.pg.eti.ksg.po.project2.Komentator;
import pl.edu.pg.eti.ksg.po.project2.Swiat;
import pl.edu.pg.eti.ksg.po.project2.Punkt;
import pl.edu.pg.eti.ksg.po.project2.Umiejetnosc;

import java.awt.*;

public class Czlowiek extends Zwierze {
    private static final int ZASIEG_RUCHU_CZLOWIEKA = 1;
    private static final int SZANSA_WYKONYWANIA_RUCHU_CZLOWIEKA = 1;
    private static final int SILA_CZLOWIEKA = 5;
    private static final int INICJATYWA_CZLOWIEKA = 4;
    private Kierunek kierunekRuchu;
    private Umiejetnosc umiejetnosc;

    public Czlowiek(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        super(TypOrganizmu.CZLOWIEK, swiat, pozycja, turaUrodzenia, SILA_CZLOWIEKA, INICJATYWA_CZLOWIEKA);
        this.setZasiegRuchu(ZASIEG_RUCHU_CZLOWIEKA);
        this.setSzansaWykonywaniaRuchu(SZANSA_WYKONYWANIA_RUCHU_CZLOWIEKA);
        kierunekRuchu = Kierunek.BRAK_KIERUNKU;
        setKolor(new Color(255, 224, 189));
        umiejetnosc = new Umiejetnosc();
    }

    @Override
    public void Akcja() {
        umiejetnosc.SprawdzWarunki();
        
        if (umiejetnosc.getCzyJestAktywna()) {
            Komentator.DodajKomentarz(OrganizmToString() + " Nieśmiertelność jest aktywna (Pozostaly czas "
                    + umiejetnosc.getCzasTrwania() + " tur)");
        }

        for (int i = 0; i < getZasiegRuchu(); i++) {
            Punkt przyszlaPozycja = ZaplanujRuch();

            if (getSwiat().CzyPoleJestZajete(przyszlaPozycja)
                    && getSwiat().CoZnajdujeSieNaPolu(przyszlaPozycja) != this) {
                Kolizja(getSwiat().CoZnajdujeSieNaPolu(przyszlaPozycja));
                break;
            } else if (getSwiat().CoZnajdujeSieNaPolu(przyszlaPozycja) != this) {
                WykonajRuch(przyszlaPozycja);
            }
        }
        kierunekRuchu = Kierunek.BRAK_KIERUNKU;
    }

  
    @Override
    public boolean SpecjalneDzialaniePodczasAtaku(Organizm atakujacy, Organizm ofiara) {
        
        if (umiejetnosc.getCzyJestAktywna() && (this == ofiara || this == atakujacy)) {
           
            Organizm przeciwnik = (this == atakujacy) ? ofiara : atakujacy;
            
            if (przeciwnik.getSila() > this.getSila()) {
                         Punkt wolnePole = LosujPoleNiezajete(this.getPozycja());
                
                if (!wolnePole.equals(this.getPozycja())) {
                    this.WykonajRuch(wolnePole);
                    Komentator.DodajKomentarz("Nieśmiertelność! Człowiek ucieka przed " + 
                            przeciwnik.TypOrganizmuToString() + " na pole [" + wolnePole.getX() + "," + wolnePole.getY() + "]");
                } 
                
                return true; 
            }
        }
        return false;
    }

    @Override
    protected Punkt ZaplanujRuch() {
        int x = getPozycja().getX();
        int y = getPozycja().getY();
        LosujPoleDowolne(getPozycja()); 
        
        if (kierunekRuchu == Kierunek.BRAK_KIERUNKU || CzyKierunekZablokowany(kierunekRuchu)) {
            return getPozycja();
        } else {
            if (kierunekRuchu == Kierunek.DOL) return new Punkt(x, y + 1);
            if (kierunekRuchu == Kierunek.GORA) return new Punkt(x, y - 1);
            if (kierunekRuchu == Kierunek.LEWO) return new Punkt(x - 1, y);
            if (kierunekRuchu == Kierunek.PRAWO) return new Punkt(x + 1, y);
            else return new Punkt(x, y);
        }
    }

    @Override
    public String TypOrganizmuToString() {
        return "Czlowiek";
    }

    public Umiejetnosc getUmiejetnosc() {
        return umiejetnosc;
    }

    public void setKierunekRuchu(Kierunek kierunekRuchu) {
        this.kierunekRuchu = kierunekRuchu;
    }
}
