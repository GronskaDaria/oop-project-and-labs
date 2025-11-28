#include "VectorOfVectors2.h"


	// domyœlny konstruktor bezparametrowy
VectorOfVectors2::VectorOfVectors2() : vektory(nullptr), size(0) {}

	//konstruktor 
VectorOfVectors2::VectorOfVectors2(const VektorNd wektory[], int wymiar)
	{
		vektory = new VektorNd[wymiar];
		size = wymiar;
		for (int i = 0; i < size; i++)
		{
			vektory[i] = wektory[i];
		}
	}
	
	//konstruktor kopij¹cy
VectorOfVectors2::VectorOfVectors2(const VectorOfVectors2& oryginal)
	{
		vektory = new VektorNd[oryginal.size];
		size = oryginal.size;
		for (int i=0; i<size; i++)
		{
			vektory[i] = oryginal.vektory[i];
		}
	}

	//destruktor
VectorOfVectors2::~VectorOfVectors2()
	{
		cout << "Zwalniam tablice pod adresem: " << this << "\n";
		delete[] vektory;
	}

void VectorOfVectors2::setVector(VektorNd wektor, int index)
	{
		vektory[index] = wektor;
	}

VectorOfVectors2& VectorOfVectors2::operator=(const VectorOfVectors2& right)
	{
	VectorOfVectors2 tmp(right);

	VektorNd* tmp2(vektory);
	vektory = tmp.vektory;
	tmp.vektory = tmp2;

	int tmp3(size);
	size = tmp.size;
	tmp.size = tmp3;

		/*if (size != right.size)
		{
			delete[] vektory;
			vektory = new VektorNd[right.size];
			size = right.size;
		}
		for (int i=0;i<size;i++)
		{
			vektory[i] = right.vektory[i];
		}*/
	return *this;
	}

void VectorOfVectors2 :: wypisz(ostream& out )
	{
		out << "\nJestem tablica wektorow pod adresem: " << this << "\n";
		out << "A tablice podrzedne to: \n";
		for (int i = 0; i < size; i++) {
			out << "[[" << i << "]] : \n";
			vektory[i].wypisz(out);
			out << "\n";
		}
	}

void VectorOfVectors2::wpisz(istream& in)
		{
			cout << "Wprowadz rozmiar tablicy : ";
			in>> size;
			vektory = new VektorNd[size];
			for (int i = 0; i < size; i++) {
				cout << " \n Dla tablicy nr " << i << " : \n";
				vektory[i].wpisz(in);
			}
		}
		


ostream& operator<<(ostream& left, VectorOfVectors2& right)
{
	right.wypisz(left);
	return left;
}
istream& operator>>(istream& left, VectorOfVectors2& right)
{
	right.wpisz(left);
	return left;
}