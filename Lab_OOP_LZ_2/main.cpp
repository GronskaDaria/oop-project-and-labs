#include <iostream>
#include "VectorNd.h"
#include "VectorOfVectors1.h"
#include "VectorOfVectors2.h"
using namespace std;

int main() 
{
	double vektor1[] = { 1,2,3 };
	double vektor2[] = { 4,5,6 };
	double vektor3[] = { 7,8,9 };
	const VektorNd wartosci[] = { VektorNd(vektor1,3), VektorNd(vektor2,3), VektorNd(vektor3,3) };
	const VektorNd wartosci2[] = { VektorNd(vektor3,3),VektorNd(vektor2,3), VektorNd(vektor1,3) };

//Zadanie 2

	VectorOfVectors2 vof2d;

	cin >> vof2d;
	cout << vof2d;
	
	VectorOfVectors2 vof2e;

	ifstream input2("input1.txt");
	input2 >> vof2e;
	cout << vof2e;
	input2.close();

//Zadanie 3
	cout << "Klasa VectorOfVectors1:\n";
	VectorOfVectors1 vof1a(wartosci,3);
	cout << "Obiekt z wartosciami wpisanymi z tablic:" << vof1a<<"\n\n";
	VectorOfVectors1 vof1b(wartosci2, 3);
	vof1a = vof1b;
	cout << "Skopiowany gleboko obiekt:" << vof1a << endl << "oraz obiekt kopiowany:" << vof1b<<"\n";
	VectorOfVectors1 vof1c(vof1b);
	cout << "Skopiowany plytko obiekt:" << vof1c << endl << "oraz obiekt kopiowany:" << vof1b << "\n";
}