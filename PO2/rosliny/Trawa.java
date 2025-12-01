package laboratorium.po2.rosliny;

import laboratorium.po2.Roslina;
import laboratorium.po2.Swiat;
import laboratorium.po2.Punkt;

import java.awt.*;

public class Trawa extends Roslina {
    private static final int SILA_TRAWY = 0;
    private static final int INICJATYWA_TRAWY = 0;

    public Trawa(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        super(TypOrganizmu.TRAWA, swiat, pozycja, turaUrodzenia, SILA_TRAWY, INICJATYWA_TRAWY);
        setKolor(new Color(107, 142, 35));
    }

    @Override
    public String TypOrganizmuToString() {
        return "Trawa";
    }
}
