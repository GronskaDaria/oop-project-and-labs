#pragma once
#include <iostream>
#include "VectorNd.h"

class VectorOfVectors1 : VektorNd {
    VektorNd vektory[10];
    int size;

public:
    VectorOfVectors1();
    VectorOfVectors1(const VectorOfVectors1& oryginal);
    VectorOfVectors1(const VektorNd wektory[], int wymiar);
    ~VectorOfVectors1();

    void setVector(VektorNd wektor, int index);
    VectorOfVectors1& operator=(const VectorOfVectors1& right);

    friend std::istream& operator>>(std::istream& left, VectorOfVectors1& right);
    friend std::ostream& operator<<(std::ostream& left, VectorOfVectors1& right);

private:
    void wypisz(std::ostream& out = std::cout);
    void wpisz(std::istream& in = std::cin);
};
