package laboratorium.po2.rosliny;

import laboratorium.po2.Roslina;
import laboratorium.po2.Swiat;
import laboratorium.po2.Punkt;
import laboratorium.po2.Organizm;
import laboratorium.po2.Zwierze;
import laboratorium.po2.Komentator;

import java.awt.*;

public class Koka extends Roslina {
    private static final int SILA_KOKA = 0;
    private static final int INICJATYWA_KOKA = 0;

    public Koka(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        super(TypOrganizmu.KOKA, swiat, pozycja, 
              turaUrodzenia, SILA_KOKA, INICJATYWA_KOKA);
        setKolor(new Color(60, 179, 113)); 
    }

    @Override
    public boolean SpecjalnaKolizja(Organizm other, Organizm ofiara) {
        if (other instanceof Zwierze) {
            Zwierze zwierze = (Zwierze) other;
            zwierze.setDodatkowyRuch(true);
            getSwiat().UsunOrganizm(ofiara);
            Komentator.DodajKomentarz(other.OrganizmToString() + " zjada Kokę i zyskuje dodatkowy ruch w następnej turze.");
        }
        return true;
    }
  
    @Override
    public String TypOrganizmuToString() {
        return "Koka";
    }
}
