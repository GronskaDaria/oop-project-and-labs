#include "Zwierze.h"
#include "Swiat.h"
#include <iostream>
#include <conio.h>
#include <stdlib.h>
#include <string>
using namespace std;

Zwierze::Zwierze(string ikona, int sila, int inicjatywa, int x, int y) : Organizm(ikona, sila, inicjatywa, x, y)
{

}

void Zwierze::akcja()
{
	postarz();
	int move = rand() % 4;
	while (!setPozycja({ getX()+ruchy[move][0], getY()+ruchy[move][1] }, false)) {
		move++;
		move %= 4;
	}
}

void Zwierze::kolizja(Organizm *inny)
{
	if(this==inny)
		return;
	if (typeid(*this) == typeid(*inny)) {
		if(getWiek()<2||inny->getWiek()<2)
			return;
		swiat->dodajLog(this,u8"Rozmnażanie");
		auto dziecko = dynamic_cast<Zwierze*>(this->dziecko());
		int move = rand() % 4;
		while (!dziecko->setPozycja({ getX()+ruchy[move%4][0], getY()+ruchy[move%4][1] },true)) {
			move++;
			if (move > 8) {
				swiat->dodajLog(this, u8"Nie ma miejsca na rozmnożenie");
				dziecko->zabij();
				return;
			}
		}
	}
	else if (getSila() > inny->getSila())
	{
		swiat->dodajLog(this, u8"Wygrał walkę");
		inny->zabij();
	}
	else if (getSila() < inny->getSila())
	{
		swiat->dodajLog(this, u8"Przegrał walkę");
		zabij();
	}
	else
	{
		swiat->dodajLog(this, u8"Równe siły");
		zabij();
		inny->zabij();
	}
}