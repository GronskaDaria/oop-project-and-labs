#include "VectorOfVectors1.h"
#include <iostream>

VectorOfVectors1::VectorOfVectors1() : size(0) {}
VectorOfVectors1::VectorOfVectors1(const VectorOfVectors1& oryginal) {
    size = oryginal.size;
    for (int i = 0; i < size; i++) {
        vektory[i] = oryginal.vektory[i];
    }
}
VectorOfVectors1::VectorOfVectors1(const VektorNd wektory[], int wymiar) {
    size = wymiar;
    for (int i = 0; i < size; i++) {
        this->vektory[i] = wektory[i];
    }
}
VectorOfVectors1::~VectorOfVectors1() {
    std::cout << "Zwalniam tablice pamieci pod adresem: " << this << "\n";
}

void VectorOfVectors1::setVector(VektorNd wektor, int index) {
    vektory[index] = wektor;
}
VectorOfVectors1& VectorOfVectors1::operator=(const VectorOfVectors1& right) {
   
    VectorOfVectors1 tmp(right);
    swap(vektory, tmp.vektory);

    int tmp3(size);
    size = tmp.size;
    tmp.size = tmp3;
    
    /* if (size != right.size) {
        size = right.size;
    }
    for (int i = 0; i < size; i++) {
        vektory[i] = right.vektory[i];
    }*/
    return *this;
}

void VectorOfVectors1::wypisz(std::ostream& out) {
    out << "\nJestem tablica wektorow pod adresem: " << this << "\n";
    out << "A tablice podrzedne to: \n";
    for (int i = 0; i < size; i++) {
        out << "[[" << i << "]] : \n";
        vektory[i].wypisz(out);
        out << "\n";
    }
}

void VectorOfVectors1::wpisz(std::istream& in) {
    std::cout << "Wprowadz rozmiar tablicy: ";
    in >> size;
    for (int i = 0; i < size; i++) {
        std::cout << "Dla tablicy nr " << i << " : \n";
        vektory[i].wpisz(in);
    }
}

std::istream& operator>>(std::istream& left, VectorOfVectors1& right) {
    right.wpisz(left);
    return left;
}

std::ostream& operator<<(std::ostream& left, VectorOfVectors1& right) {
    right.wypisz(left);
    return left;
}
