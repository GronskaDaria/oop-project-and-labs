package laboratorium.po2;

import java.util.Random;

public abstract class Roslina extends Organizm {

    protected Roslina(TypOrganizmu typOrganizmu, Swiat swiat,
                      Punkt pozycja, int turaUrodzenia, int sila, int inicjatywa) {
        super(typOrganizmu, swiat, pozycja, turaUrodzenia, sila, inicjatywa);
        setSzansaRozmnazania(0.3);
    }

    @Override
    public void Akcja() {
        Random rand = new Random();
        int upperbound = 10;
        int tmpLosowanie = rand.nextInt(upperbound);
        if (tmpLosowanie < getSzansaRozmnazania() * 1) Rozprzestrzenianie();
    }

    @Override
    public boolean CzyJestZwierzeciem() {
        return false;
    }


    protected void Rozprzestrzenianie() {
        Punkt tmp1Punkt = this.LosujPoleNiezajete(getPozycja());
        if (tmp1Punkt.equals(getPozycja())) return;
        else {
            Organizm tmpOrganizm = NowyOrganizm.StworzNowyOrganizm(getTypOrganizmu(), this.getSwiat(), tmp1Punkt);
            Komentator.DodajKomentarz("Wyrasta roslina z " + tmpOrganizm.OrganizmToString());
            getSwiat().DodajOrganizm(tmpOrganizm);
        }
    }

    @Override
    public void Kolizja(Organizm other) {
    }

}
