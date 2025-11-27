#include "Complex.h"
#include <iostream>
#include <cmath> 
using namespace std;

unsigned char a = 0260;
const double PI = atan(1.0) * 4;
//Konstruktor
Complex::Complex(double re, double im)
{
	cout << "Konstruktor Complex: Re = " << re << ", Im = " << im << "\n";
	this->re = re;
	this->im = im;
}

//Destruktor
Complex::~Complex(void)
{
	cout << "Destruktor Complex: Re = " << this->re << ", Im = " << this->im << "\n";
}

//Operator dodawania
Complex Complex::operator+(Complex &right)
{
	return Complex(this->re +right.re, this->im+right.im);
}

Complex Complex::operator-(Complex & right)
{
	return Complex(this->re -right.re, this->im-right.im);
}



//Operator <<
ostream& operator<<(ostream &out,Complex &c)
{
	out << "Liczba zespolona: " << c.re << " + " << c.im << "i\n";
	return out;
}

double Complex::modul() {
	return sqrt(this->re*re+im*im);
}

void Complex::kat() {
	double katComplex = atan2(im, re) * (180.0 / PI);
	cout << int(katComplex)<<a<<"\n";
}

