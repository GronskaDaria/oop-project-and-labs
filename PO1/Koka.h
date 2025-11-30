#pragma once
#include "Roslina.h"
class Koka :
	public Roslina
{
public:
	Koka(int x, int y);
	void kolizja(Organizm* inny);
	Organizm* dziecko();
};