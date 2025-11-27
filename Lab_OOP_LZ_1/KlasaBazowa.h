#pragma once
#include <iostream>

using namespace std;

class Wartosc_Liczbowa
{
public:
	unsigned char a = 0260;
	virtual double modul() = 0; 
	virtual void kat() = 0;
	virtual void wypisz(ostream& out) = 0;
	virtual ~Wartosc_Liczbowa()
	{}
	virtual void PrzykladOverriding() 
	{
		cout << "To sie wyswietla z klasy bazowej \n";
	};
};
//
//1.1 Podstawowe typy danych 
// Typy wbudowane w jêzyk C++, reprezentuj¹ce pojedyncze wartoœci.
// Kompilator rozpoznaje te wbudowane typy i ma wbudowane regu³y, które
// okreœlaj¹, jakie operacje mo¿na wykonywaæ na nich, oraz sposób ich
// konwertowania na inne typy podstawowe. 


//1.2 Z³o¿one typy danych
//To typy danych utworzone przez u¿ytkownika, które umo¿liwiaj¹ przechowywanie bardziej z³o¿onych 
//struktur danych ni¿ podstawowe typy.Typy z³o¿one obejmuj¹ typy tablic, typy funkcji, 
//typy klas(lub struktury), typy unii, wyliczenia, odwo³ania i wskaŸniki do niestacjonalnych sk³adowych klas.

//1.3 Klasa, obiekt i struktura
//   Pojêcie			|			Podobieñstwa				|		Ró¿nice
//----------------------|---------------------------------------|---------------------------------------------------------------
//Klasa vs Struktura	|	Oba pozwalaj¹ definiowaæ dane i		|	Klasy : domyœlnie prywatne; 
//				    	|	funkcje.							|	Struktury: domyœlnie publiczne.Klasy lepiej wspieraj¹ OOP.
//----------------------|---------------------------------------|---------------------------------------------------------------
//Klasa vs Obiekt		|	Klasa definiuje, czym mo¿e byæ		| Klasa : szablon; 
//					    |	obiekt.								| Obiekt: instancja tej klasy, posiada konkretne dane.
//----------------------|---------------------------------------|--------------------------------------------------------------
//Struktura vs Obiekt   |	Obiekt struktury przechowuje dane i | Struktura to definicja; Obiekt struktury to instancja
//					    |	mo¿e mieæ funkcje.					|
//----------------------------------------------------------------------------------------------------------------------------

//2.1 Metoda wirtualna
//Metoda wirtualna to funkcja zadeklarowana w klasie bazowej z u¿yciem s³owa kluczowego virtual.
//Jej g³ówn¹ cech¹ jest mo¿liwoœæ nadpisania w klasach pochodnych

//2.2 Czysta metoda wirtualna
//Czysta metoda wirtualna to metoda zadeklarowana w klasie bazowej, ale bez implementacji.
//Klasy pochodne musz¹ j¹ nadpisaæ, aby mo¿na by³o utworzyæ ich obiekty.Oznacza siê j¹ przy pomocy = 0 
//w deklaracji.

//2.3
//dotycz¹ takich paradygmatów: dziedziczenie, abstrakcja,polimorfizm

//3.1
//Klasa Wartosc_Liczbowa jest abstrakcyjna, bo zawiera czyst¹ metodê wirtualn¹ module()