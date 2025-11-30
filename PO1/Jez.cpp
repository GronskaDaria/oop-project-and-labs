#include "Jez.h"
#include "Zwierze.h"
#include "Swiat.h"

Jez::Jez(int x, int y) : Zwierze(USE_EMOJI ? u8"🦔" : "Je", 2, 3, x, y)
{
}

void Jez::kolizja(Organizm* inny)
{
    if (inny->getSila() > getSila()) {
        swiat->dodajLog(this, u8"Ginie w walce, zatruwając i unieruchamiając przeciwnika!");
        zabij();

        swiat->dodajLog(inny, u8"Unieruchomiony przez Jeża na 2 tury!");
        inny->unieruchomNaTury(2);
    }

}

Jez* Jez::dziecko()
{
    return new Jez(*this);
}