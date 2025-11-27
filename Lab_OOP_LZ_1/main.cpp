#include "Complex.h"
#include "Liczba.h"
#include "OverridingOverloading.h"
#include <iostream>
using namespace std;

int main() {
    //3.1
    Liczba l1(-1.0), l2(3.0);
    Complex c1(-1.0, -1.0), c2(1.0, -1.0);
    // Tworzenie obiektów przez wskaŸniki
    Wartosc_Liczbowa* w1 = new Liczba(7.5);
    Wartosc_Liczbowa* w2 = new Complex(2.0, 3.0);


    //cout << "Modul l1: " << l1.modul() << endl;
    cout << "Kat l1:" ;
    l1.kat();
    //cout << "\nModul c1: " << c1.modul() << endl;
    cout << "Kat c1:";
    c1.kat();

    Liczba  l3 = l1 - l2;
    cout << "Odejmowanie l1 i l2: " << l3;

    Complex c3 = c1 - c2;
    cout << "Odejmowanie c1 i c2: " << c3;

    ////3.2
    Wartosc_Liczbowa* tabObj[] = { w1,w2 };

    for (int i = 0; i < 2; ++i)
    {
        cout << "Modul przez wskaznik : ";
         tabObj[i]->kat();
    };


   /* w1->PrzykladOverriding();
    Przyklad(5);*/

    delete w1;
    delete w2;

     
    return 0;
}