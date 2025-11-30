#include "Koka.h"
#include "Swiat.h"

Koka::Koka(int x, int y) : Roslina(USE_EMOJI ? u8"🍃" : "Ko", 0, x, y)
{
}

void Koka::kolizja(Organizm* inny)
{
	string nazwa = typeid(*inny).name();
	swiat->dodajLog(this, u8"Dodała ruch " + nazwa.substr(6));
	inny->akcja();
	zabij();
	
}

Organizm* Koka::dziecko()
{
	return new Koka(*this);

}