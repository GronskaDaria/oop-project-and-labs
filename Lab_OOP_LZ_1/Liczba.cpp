#include "Liczba.h"
#include <iostream>
#include <cmath> 
using namespace std;


const double PI = atan(1.0) * 4;

// Konstruktor
Liczba::Liczba(double re) {
    cout << "Konstruktor Liczba = " << re << "\n";
    this->re = re;
}

// Destruktor (TODO 1)
Liczba::~Liczba(void) {
    cout << "Destruktor Liczba = " << this->re << "\n";
}

Liczba Liczba::operator-(Liczba& right)
{
    return Liczba(this->re - right.re);
}

// Metoda modul() (TODO 2)
double Liczba::modul() {
    return abs(this->re);
}

void Liczba::kat() {
    cout<< (atan(re)*(180/PI))<<a<<"\n";
}

// Operator << dla wyœwietlania obiektu
std::ostream& operator<<(std::ostream& out, Liczba& l) {
    out << "Liczba rzeczywista: " << l.re << "\n";
    return out;
}