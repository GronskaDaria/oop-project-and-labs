#pragma once
#include "Zwierze.h"
class Jez :
	public Zwierze
{
public:
	Jez(int x, int y);
	void kolizja(Organizm* inny);
	Jez* dziecko();
};