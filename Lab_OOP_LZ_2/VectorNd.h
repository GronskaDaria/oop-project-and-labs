#pragma once
#include <iostream>
#include <fstream>

using namespace std;
class VektorNd {
	double* _liczby; //pole ze wskaünikiem liczb
	int _wymiar; //wymiar wektora
public:
	VektorNd() : _liczby(nullptr), _wymiar(0) {};

	VektorNd(const double liczby[], int wymiar) { //konstruktor
		_liczby = new double[wymiar];
		_wymiar = wymiar;
		for (int i = 0; i < _wymiar; i++)
			_liczby[i] = liczby[i];

	}
	VektorNd(const VektorNd& oryginal) { //konstruktor kopiujπcy
		_liczby = new double[oryginal._wymiar];
		_wymiar = oryginal._wymiar;
		for (int i = 0; i < _wymiar; i++)
			_liczby[i] = oryginal._liczby[i];

	}
	~VektorNd() {
		delete[] _liczby;
	}

	void getCoefs(double liczby[]) { //metoda publiczna
		for (int i = 0; i < _wymiar; i++)
			liczby[i] = _liczby[i];
	}
	void setCoef(double wartosc, int index) {
		_liczby[index] = wartosc;
	}
	double getCoef(int index) { //pomocznicza metoda
		return _liczby[index];
	}
	int getDim() { //pomocznicza metoda
		return _wymiar;
	}

	VektorNd& operator=(const VektorNd& right) { //implementacja operatora przypisania
		if (_wymiar != right._wymiar) { //gdy zgodne wymiary nie potrzeba realokowaÊ pamiÍci
			delete[] _liczby;
			_liczby = new double[right._wymiar]; //alokacja pamiÍci dla nowego wymiaru
			_wymiar = right._wymiar;
		}
		for (int i = 0; i < _wymiar; i++)
			_liczby[i] = right._liczby[i];
		return *this;
	}


private:
	void wypisz(ostream& out = cout) {
		out << "Jestem wektorem pod adresem: " << this << endl;
		for (int i = 0; i < _wymiar; i++)
			out << "[" << i << "] = " << _liczby[i]<<"\t";
		cout << "\n";
	}

	void wpisz(istream& in = cin) {
		cout << "Wprowadz wymiar wektora: ";
		in >> _wymiar;
		_liczby = new double[_wymiar];
		for (int i = 0; i < _wymiar; i++) {
			cout << "Wpisz element " << i << ": ";
			in >> _liczby[i];
		}
	}


	friend ostream& operator<<(ostream& left, VektorNd& right);
	//wewnπtrz klasy naleøy zadeklarowaÊ fakt zaprzyjaünienia
	friend class VectorOfVectors2;
	friend class VectorOfVectors1;

};






