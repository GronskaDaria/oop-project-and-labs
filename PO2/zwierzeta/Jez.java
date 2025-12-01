package laboratorium.po2.zwierzeta;

import laboratorium.po2.Zwierze;
import laboratorium.po2.Swiat;
import laboratorium.po2.Punkt;
import laboratorium.po2.Organizm;
import laboratorium.po2.Komentator;

import java.awt.*;

public class Jez extends Zwierze {
    private static final int ZASIEG_RUCHU_JEZA = 1;
    private static final int SZANSA_WYKONYWANIA_RUCHU_JEZ = 1;
    private static final int SILA_JEZA = 2;
    private static final int INICJATYWA_JEZA = 3;

    public Jez(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        super(TypOrganizmu.JEZ, swiat, pozycja, turaUrodzenia, SILA_JEZA, INICJATYWA_JEZA);
        this.setZasiegRuchu(ZASIEG_RUCHU_JEZA);
        this.setSzansaWykonywaniaRuchu(SZANSA_WYKONYWANIA_RUCHU_JEZ);
        setKolor(new Color(102, 51, 0)); 
    }
    @Override
    public boolean SpecjalnaKolizja(Organizm other, Organizm ofiara) {
        if (this.getSila() < other.getSila()) { 
            if (other instanceof Zwierze) { 
                Zwierze napastnik = (Zwierze) other;
                napastnik.setUnieruchomiony(2); 
                Komentator.DodajKomentarz(other.OrganizmToString() + " pokonuje Jeża, ale zostaje ranny i nie może się poruszać przez 2 tury.");
            }
            getSwiat().UsunOrganizm(ofiara); 
        } else {
            super.Kolizja(other); 
        }
    return true;
    }

    @Override
    public String TypOrganizmuToString() {
        return "Jez";
    }
}
