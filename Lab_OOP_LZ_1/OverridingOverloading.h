#include "KlasaBazowa.h"
using namespace std;

class Przyklad : public Wartosc_Liczbowa
{
public:

	Przyklad(int a)
	{
		PrzykladowaFunkcja();
		PrzykladowaFunkcja(a);
		PrzykladOverriding();
		cout << "\n";
	};
	~Przyklad(void) {}; 
	double modul() { return 0; }

	void PrzykladowaFunkcja()
	{
		cout << "Przykladowa funkcja\n";
	}

	void PrzykladowaFunkcja(int a)
	{
		cout << "Przykladowa funkcja z przeciazeniem (overloading). Wypisuje zmienna a: " << a << ".\n";
	}

	void PrzykladOverriding() override
	{
		cout << "Wywolanie z nadpisaniem funkcji PrzykladOverride().\n";
	}

	void wypisz(ostream& out) {}
};