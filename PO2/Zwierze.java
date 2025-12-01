package laboratorium.po2;

import java.util.Random;

public abstract class Zwierze extends Organizm {
    private int zasiegRuchu;
    private double szansaWykonywaniaRuchu;
    private boolean dodatkowyRuch; 
    private int unieruchomiony = 0;

    public Zwierze(TypOrganizmu typOrganizmu, Swiat swiat,
                   Punkt pozycja, int turaUrodzenia, int sila, int inicjatywa) {
        super(typOrganizmu, swiat, pozycja, turaUrodzenia, sila, inicjatywa);
        setCzyRozmnazalSie(false);
        setSzansaRozmnazania(1);
         this.dodatkowyRuch = false; 
    }

    @Override
    public void Akcja() {
    if (isUnieruchomiony()) {
        zmniejszUnieruchomienie(); 
        Komentator.DodajKomentarz(OrganizmToString() + " jest unieruchomiony i nie wykonuje ruchu.");
        return; 
    }

    int ruchyDoWykonania = dodatkowyRuch ? zasiegRuchu + 1 : zasiegRuchu; 
    dodatkowyRuch = false; 

    for (int i = 0; i < ruchyDoWykonania; i++) {
        Punkt przyszlaPozycja = ZaplanujRuch();

        if (getSwiat().isPoleOccupied(przyszlaPozycja)
                && getSwiat().CoZnajdujeSieNaPolu(przyszlaPozycja) != this) {
            Kolizja(getSwiat().CoZnajdujeSieNaPolu(przyszlaPozycja));
            break;
        } else if (getSwiat().CoZnajdujeSieNaPolu(przyszlaPozycja) != this) WykonajRuch(przyszlaPozycja);
    }
}


    @Override
    public void Kolizja(Organizm other) {
        if (getTypOrganizmu() == other.getTypOrganizmu()) {
            Random rand = new Random();
            int tmpLosowanie = rand.nextInt(100);
            if (tmpLosowanie < getSzansaRozmnazania() * 100) Rozmnazanie(other);
        } else {
            if (other.SpecjalnaKolizja(this, other)) return;
            if (SpecjalnaKolizja(this, other)) return;

            if (getSila() >= other.getSila()) {
                getSwiat().UsunOrganizm(other);
                WykonajRuch(other.getPozycja());
                Komentator.DodajKomentarz(OrganizmToString() + " zabija " + other.OrganizmToString());
            } else {
                getSwiat().UsunOrganizm(this);
                Komentator.DodajKomentarz(other.OrganizmToString() + " zabija " + OrganizmToString());
            }
        }
    }

    public boolean getDodatkowyRuch() {
        return dodatkowyRuch;
    }

    public void setDodatkowyRuch(boolean dodatkowyRuch) {
        this.dodatkowyRuch = dodatkowyRuch;
    }

    @Override
    public boolean CzyJestZwierzeciem() {
        return true;
    }

    protected Punkt ZaplanujRuch() {
        Random rand = new Random();
        int upperbound = 100;
        int tmpLosowanie = rand.nextInt(upperbound);
        if (tmpLosowanie >= (int) (szansaWykonywaniaRuchu * 100)) return getPozycja();
        else return LosujPoleDowolne(getPozycja());
    }

    private void Rozmnazanie(Organizm other) {
        if (this.getCzyRozmnazalSie() || other.getCzyRozmnazalSie()) return;
        Punkt tmp1Punkt = this.LosujPoleNiezajete(getPozycja());
        if (tmp1Punkt.equals(getPozycja())) {
            Punkt tmp2Punkt = other.LosujPoleNiezajete(other.getPozycja());
            if (tmp2Punkt.equals(other.getPozycja())) return;
            else {
                Organizm tmpOrganizm = NowyOrganizm.StworzNowyOrganizm(getTypOrganizmu(), this.getSwiat(), tmp2Punkt);
                Komentator.DodajKomentarz("Urodzil sie " + tmpOrganizm.OrganizmToString());
                getSwiat().DodajOrganizm(tmpOrganizm);
                setCzyRozmnazalSie(true);
                other.setCzyRozmnazalSie(true);
            }
        } else {
            Organizm tmpOrganizm = NowyOrganizm.StworzNowyOrganizm(getTypOrganizmu(), this.getSwiat(), tmp1Punkt);
            Komentator.DodajKomentarz("Urodzil sie " + tmpOrganizm.OrganizmToString());
            getSwiat().DodajOrganizm(tmpOrganizm);
            setCzyRozmnazalSie(true);
            other.setCzyRozmnazalSie(true);
        }
    }

    public int getZasiegRuchu() {
        return zasiegRuchu;
    }

    public void setZasiegRuchu(int zasiegRuchu) {
        this.zasiegRuchu = zasiegRuchu;
    }

    public double getSzansaWykonywaniaRuchu() {
        return szansaWykonywaniaRuchu;
    }

    public void setSzansaWykonywaniaRuchu(double szansaWykonywaniaRuchu) {
        this.szansaWykonywaniaRuchu = szansaWykonywaniaRuchu;
    }
    
    public void setUnieruchomiony(int tury) {
    this.unieruchomiony = tury;
}

public void zmniejszUnieruchomienie() {
    if (unieruchomiony > 0) {
        unieruchomiony--;
    }
}

public boolean isUnieruchomiony() {
    return unieruchomiony > 0;
}
}
