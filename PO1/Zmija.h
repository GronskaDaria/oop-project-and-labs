#pragma once
#include "Zwierze.h"
class Zmija :
	public Zwierze
{
public:
	Zmija(int x, int y);
	void kolizja(Organizm* inny);
	Zmija* dziecko();
};
