package laboratorium.po2.zwierzeta;

import laboratorium.po2.Zwierze;
import laboratorium.po2.Swiat;
import laboratorium.po2.Punkt;
import laboratorium.po2.Organizm;
import laboratorium.po2.Komentator;

import java.awt.*;

public class Zmija extends Zwierze {
    private static final int ZASIEG_RUCHU_ZMIJI = 1;
    private static final int SZANSA_WYKONYWANIA_RUCHU_ZMIJA = 1;
    private static final int SILA_ZMIJI = 2;
    private static final int INICJATYWA_ZMIJI = 3;

    public Zmija(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        super(TypOrganizmu.ZMIJA, swiat, pozycja, turaUrodzenia, SILA_ZMIJI, INICJATYWA_ZMIJI);
        this.setZasiegRuchu(ZASIEG_RUCHU_ZMIJI);
        this.setSzansaWykonywaniaRuchu(SZANSA_WYKONYWANIA_RUCHU_ZMIJA);
        setKolor(new Color(34, 139, 34)); 
    }


    @Override
public boolean SpecjalnaKolizja(Organizm other, Organizm ofiara) {
    if (this.getSila() < other.getSila()) {
        if (other instanceof Zwierze) {
            Zwierze napastnik = (Zwierze) other;
            
            getSwiat().UsunOrganizm(this); 
            getSwiat().UsunOrganizm(napastnik); 

            Komentator.DodajKomentarz("Żmija ginie w walce, ale " + napastnik.OrganizmToString() + " zostaje zatruty i również ginie.");
        }
        return true;
    } else {
        super.Kolizja(other);
    }
    return true;
}


    @Override
    public String TypOrganizmuToString() {
        return "Zmija";
    }
}
