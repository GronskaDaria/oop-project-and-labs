#include <iostream>
#include "KlasaBazowa.h"

using namespace std;

class Liczba : public Wartosc_Liczbowa
{
	double re; 
public:
	double modul();
	void kat();
	Liczba(double re);
	Liczba operator- (Liczba&);
	~Liczba(void);
	friend ostream& operator<< (ostream& out, Liczba& l);
	void wypisz(ostream& out)
	{
		out << *this;
	}
}; 
