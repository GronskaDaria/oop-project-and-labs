#pragma once
#include <iostream>
#include "VectorNd.h"

using namespace std;

class VectorOfVectors2 {
    VektorNd* vektory;  // Dynamiczna tablica wektorów
    int size;           // Liczba wektorów w tablicy

public:
    // Konstruktory i destruktor
    VectorOfVectors2();
    VectorOfVectors2(const VektorNd wektory[], int wymiar);
    VectorOfVectors2(const VectorOfVectors2& oryginal);
    ~VectorOfVectors2();

    // Operator przypisania
    VectorOfVectors2& operator=(const VectorOfVectors2& right);

    // Ustawienie wektora na okreœlonym indeksie
    void setVector(VektorNd wektor, int index);

    // Operatory strumieniowe
    friend ostream& operator<<(ostream& left, VectorOfVectors2& right);
    friend istream& operator>>(istream& left, VectorOfVectors2& right);

private:
    void wypisz(ostream& out = cout);
    void wpisz(istream& in = cin);
};
