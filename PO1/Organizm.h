#pragma once
#include <string>
#include <iostream>
#define USE_EMOJI 1
using namespace std;
class Swiat;
class Organizm
{
	int sila;
	int inicjatywa;
	int wiek;
	string ikona;
	pair<int, int> pozycja;
	pair<int,int> poprzedniaPozycja;
protected:
	Swiat* swiat;
	const int ruchy[4][2] = { {0,1},{1,0},{0,-1},{-1,0} };

public:
	Organizm(string ikona, int sila, int inicjatywa, int x, int y);
	Organizm(const Organizm& a);
	virtual void akcja()=0;
	virtual void kolizja(Organizm* inny)=0;
	virtual Organizm* dziecko()=0;
	virtual string toString();
	void rysowanie();
	int getSila();
	int getInicjatywa();
	int getWiek();
	string getIkona();
	void zabij();
	void postarz(int wartosc=1);
	int getX();
	int getY();
	bool czyUnieruchomiony();
	void unieruchomNaTury(int tury);
	void zmniejszUnieruchomienie();
	bool setPozycja(pair<int, int> pozycja, bool musiBycPuste);
	static int pierwszenstwo(Organizm* a, Organizm* b);
	int immobilizedTurns;
};

