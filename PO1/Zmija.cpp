#include "Zmija.h"
#include "Zwierze.h"
#include "Swiat.h"

Zmija::Zmija(int x, int y) : Zwierze(USE_EMOJI ? u8"🐍" : "Zm", 2, 3, x, y)
{
}

void Zmija::kolizja(Organizm* inny)
{
            if (inny->getSila() > getSila()) {
                swiat->dodajLog(this, u8"Ginie w walce, zatruwając przeciwnika!");
                zabij(); 

                swiat->dodajLog(inny, u8"Zatruty przez Zmiję!");
                inny->zabij();  
            }
           
}

Zmija* Zmija::dziecko()
{
	return new Zmija(*this);
}