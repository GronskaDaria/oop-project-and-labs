package laboratorium.po2.zwierzeta;

import laboratorium.po2.Zwierze;
import laboratorium.po2.Swiat;
import laboratorium.po2.Punkt;
import java.awt.*;

public class Owca extends Zwierze {
    private static final int ZASIEG_RUCHU_OWCY = 1;
    private static final int SZANSA_WYKONYWANIA_RUCHU_OWCA = 1;
    private static final int SILA_OWCY = 4;
    private static final int INICJATYWA_OWCY = 4;




    public Owca(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        super(TypOrganizmu.OWCA, swiat, pozycja, turaUrodzenia, SILA_OWCY, INICJATYWA_OWCY);
        this.setZasiegRuchu(ZASIEG_RUCHU_OWCY);
        this.setSzansaWykonywaniaRuchu(SZANSA_WYKONYWANIA_RUCHU_OWCA);
        setKolor(new Color(253, 245, 230));
    }

    @Override
    public String TypOrganizmuToString() {
        return "Owca";
    }
}
