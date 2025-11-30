#include "Roslina.h"
#include "Swiat.h"

Roslina::Roslina(string ikona, int sila, int x, int y): Organizm(ikona, sila, 0, x, y)
{
}

void Roslina::akcja()
{
	bool zasieje = rand() % 50 == 0;
	if (zasieje) {
		auto dziecko = dynamic_cast<Roslina*>(this->dziecko());
		int move = rand() % 4;
		while (!dziecko->setPozycja({ getX() + ruchy[move % 4][0], getY() + ruchy[move % 4][1] }, true)) {
			move++;
			if (move > 8) {
				swiat->dodajLog(this, "Nie ma miejsca na rozsianie");
				dziecko->zabij();
				return;
			}
		}
		swiat->dodajLog(this, u8"Rozsiała się");
	}
}

void Roslina::kolizja(Organizm* inny)
{
	if(this==inny)
		return;
	if (getSila() > inny->getSila())
	{
		swiat->dodajLog(this, u8"Zatruła napastnika");
		inny->zabij();
	}
	else if (getSila() < inny->getSila())
	{
		swiat->dodajLog(this, u8"Została zdeptana");
		zabij();
	}
	else
	{
		swiat->dodajLog(this, u8"Równe siły");
		zabij();
		inny->zabij();
	}
}
