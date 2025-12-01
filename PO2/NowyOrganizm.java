package laboratorium.po2;

import laboratorium.po2.rosliny.*;
import laboratorium.po2.zwierzeta.*;


public class NowyOrganizm {
    public static Organizm StworzNowyOrganizm
            (Organizm.TypOrganizmu typOrganizmu, Swiat swiat, Punkt pozycja) {
        return switch (typOrganizmu) {
            case WILK -> new Wilk(swiat, pozycja, swiat.getNumerTury());
            case OWCA -> new Owca(swiat, pozycja, swiat.getNumerTury());
            case JEZ -> new Jez(swiat, pozycja, swiat.getNumerTury());
            case LIS -> new Lis(swiat, pozycja, swiat.getNumerTury());
            case ZMIJA -> new Zmija(swiat, pozycja, swiat.getNumerTury());
            case TRAWA -> new Trawa(swiat, pozycja, swiat.getNumerTury());
            case KOKA -> new Koka(swiat, pozycja, swiat.getNumerTury());
            case MLECZ -> new Mlecz(swiat, pozycja, swiat.getNumerTury());
            default -> null;
        };
    }
}
