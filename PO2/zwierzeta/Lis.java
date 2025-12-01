
package laboratorium.po2.zwierzeta;


import laboratorium.po2.Zwierze;
import laboratorium.po2.Swiat;
import laboratorium.po2.Punkt;
import laboratorium.po2.Organizm;

import java.awt.*;
import java.util.Random;

public class Lis extends Zwierze {
    private static final int ZASIEG_RUCHU_LISA = 1;
    private static final int SZANSA_WYKONYWANIA_RUCHU_LISA = 1;
    private static final int SILA_LISA = 3;
    private static final int INICJATYWA_LISA = 7;

    public Lis(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        super(TypOrganizmu.LIS, swiat, pozycja, turaUrodzenia, SILA_LISA, INICJATYWA_LISA);
        this.setZasiegRuchu(ZASIEG_RUCHU_LISA);
        this.setSzansaWykonywaniaRuchu(SZANSA_WYKONYWANIA_RUCHU_LISA);
        setKolor(new Color(255, 128, 0));
    }

    @Override
    public String TypOrganizmuToString() {
        return "Lis";
    }

    @Override
    public Punkt LosujPoleDowolne(Punkt pozycja) {
        OdblokujWszystkieKierunki();
        int pozX = pozycja.getX();
        int pozY = pozycja.getY();
        int sizeX = getSwiat().getSizeX();
        int sizeY = getSwiat().getSizeY();
        int ileKierunkowMozliwych = 0;
        Organizm tmpOrganizm;

        if (pozX == 0) ZablokujKierunek(Kierunek.LEWO);
        else {
            tmpOrganizm = getSwiat().getPlansza()[pozY][pozX - 1];
            if (tmpOrganizm != null && tmpOrganizm.getSila() > this.getSila()) {
                ZablokujKierunek(Kierunek.LEWO);
            } else ileKierunkowMozliwych++;
        }

        if (pozX == sizeX - 1) ZablokujKierunek(Kierunek.PRAWO);
        else {
            tmpOrganizm = getSwiat().getPlansza()[pozY][pozX + 1];
            if (tmpOrganizm != null && tmpOrganizm.getSila() > this.getSila()) {
                ZablokujKierunek(Kierunek.PRAWO);
            } else ileKierunkowMozliwych++;
        }

        if (pozY == 0) ZablokujKierunek(Kierunek.GORA);
        else {
            tmpOrganizm = getSwiat().getPlansza()[pozY - 1][pozX];
            if (tmpOrganizm != null && tmpOrganizm.getSila() > this.getSila()) {
                ZablokujKierunek(Kierunek.GORA);
            } else ileKierunkowMozliwych++;
        }

        if (pozY == sizeY - 1) ZablokujKierunek(Kierunek.DOL);
        else {
            tmpOrganizm = getSwiat().getPlansza()[pozY + 1][pozX];
            if (tmpOrganizm != null && tmpOrganizm.getSila() > this.getSila()) {
                ZablokujKierunek(Kierunek.DOL);
            } else ileKierunkowMozliwych++;
        }

        if (ileKierunkowMozliwych == 0) return new Punkt(pozX, pozY);
        while (true) {
            Random rand = new Random();
            int upperbound = 100;
            int tmpLosowanie = rand.nextInt(upperbound);
            if (tmpLosowanie < 25 && !CzyKierunekZablokowany(Kierunek.LEWO))
                return new Punkt(pozX - 1, pozY);
            else if (tmpLosowanie >= 25 && tmpLosowanie < 50 && !CzyKierunekZablokowany(Kierunek.PRAWO))
                return new Punkt(pozX + 1, pozY);
            else if (tmpLosowanie >= 50 && tmpLosowanie < 75 && !CzyKierunekZablokowany(Kierunek.GORA))
                return new Punkt(pozX, pozY - 1);
            else if (tmpLosowanie >= 75 && !CzyKierunekZablokowany(Kierunek.DOL))
                return new Punkt(pozX, pozY + 1);
        }
    }
}