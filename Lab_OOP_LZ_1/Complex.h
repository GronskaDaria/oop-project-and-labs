#include "KlasaBazowa.h"
#include <ostream>
class Complex : public Wartosc_Liczbowa
{
protected:
	double re;
	double im; 
public:
	~Complex(void);
	Complex(double re = 0, double im = 0);
	Complex operator +(Complex&);
	Complex operator -(Complex&);
	friend std::ostream& operator<< (std::ostream& out, Complex& l);
	double modul();
	void kat();
	void wypisz(std::ostream& out)
	{
		out << *this;
	}
}; 

// 2.1 operator zaprzyjaŸniony
//Operator zaprzyjaŸniony(ang. friend ) to funkcja zdefiniowana jako przyjaciel klasy,
//co pozwala jej na dostêp do prywatnych i chronionych atrybutów tej klasy.

//2.2  dostêp  dla atrybutów klasy Complex
//jako chronione(s³owo kluczowe protected).Oznacza to, ¿e atrybuty re i im s¹ dostêpne w 
//obrêbie klasy Complex, jej klas pochodnych, ale nie s¹ dostêpne z zewn¹trz.

//2.3  konstruktory dla klasy Complex
//Konstruktor domyœlny(mo¿e dzia³aæ tak¿e jako konstruktor parametryczny).Umo¿liwia tworzenie
//obiektów z domyœlnymi wartoœciami czêœci rzeczywistej i urojonej(odpowiednio 0).

