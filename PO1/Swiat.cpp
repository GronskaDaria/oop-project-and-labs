#include "Swiat.h"
#include "Organizm.h"
#include "Wilk.h"
#include "Owca.h"
#include "Lis.h"
#include "Trawa.h"
#include "Koka.h"
#include "Mlecz.h"
#include "Zmija.h"
#include "Jez.h"

Organizm* Swiat::getOrganizm(pair<int,int> pozycja)
{
    if(pozycja.first < 0 || pozycja.first >= szerokosc || pozycja.second < 0 || pozycja.second >= wysokosc)
	    return nullptr;
    for (Organizm* o : organizmy)
    {
        if (o->getX() == pozycja.first && o->getY() == pozycja.second)
        {
			return o;
		}
	}
	return nullptr;
}

void Swiat::dodajLog(Organizm* zrodlo, string log)
{
    string nazwa = typeid(*zrodlo).name();
    nazwa = zrodlo->getIkona()+"(" + nazwa.substr(6, nazwa.length())+")";
    wpisyLogow++;
	cout << "\033[" << wysokosc+4+wpisyLogow << ";0H";
    cout << nazwa << log;
}

void Swiat::wyczyscLogi()
{
    cout << "\x1B[2J";
	wpisyLogow = 0;
}

void Swiat::dodajOrganizm(Organizm* organizm)
{
    organizmy.push_back(organizm);
}

Swiat::Swiat(int szerokosc, int wysokosc)
{
    this->szerokosc = szerokosc;
    this->wysokosc = wysokosc;
    narysowany = false;
    wpisyLogow = 0;
    tura = 0;
}

Swiat* Swiat::instance = nullptr;

Swiat* Swiat::getInstance() {
    return instance;
}

Swiat* Swiat::getInstance(int szerokosc, int wysokosc) {
    if (instance != nullptr)
        delete instance;
    instance = new Swiat(szerokosc, wysokosc);
    return instance;
}

void Swiat::poczatkowaPopulacja() {
    
    new Wilk(rand() % szerokosc, rand() % wysokosc);
    new Wilk(rand() % szerokosc, rand() % wysokosc);
    new Wilk(rand() % szerokosc, rand() % wysokosc);
    new Wilk(rand() % szerokosc, rand() % wysokosc);
    new Wilk(rand() % szerokosc, rand() % wysokosc);
    new Wilk(rand() % szerokosc, rand() % wysokosc);
  

}

void Swiat::wykonajTure()
{
    wyczyscLogi();
    tura++;
    organizmy.sort(Organizm::pierwszenstwo);
    for (Organizm* o : organizmy)
    {
        if (o->getSila() > -1)
        {
            if (!o->czyUnieruchomiony()) {
                o->akcja();
            }
            else {
                dodajLog(o, u8"Jest unieruchomiony i nie może się poruszyć!");
            }
            o->zmniejszUnieruchomienie();
        }
        else
            break;
	}
}

void Swiat::rysujSwiat()
{
    cout << "\033[0;0H";
    for (int i = 0; i < szerokosc * 2 + 2; i++)
    {
        cout << "*";
    }
    cout << endl;
    for (int i = 0; i < wysokosc; i++) {
        cout << "*";
        for (int j = 0; j < szerokosc * 2; j++)
        {
            cout << " ";
        }
        cout << "*\n";
    }
    for (int i = 0; i < szerokosc * 2 + 2; i++)
    {
        cout << "*";
    }
    cout << endl;
    for (Organizm* o : organizmy)
    {
        if(o->getSila()>-1)
		    o->rysowanie();
    }
    cout << "\033["<<wysokosc+3<<";0H";
    cout << "TURA " << tura;
}

int Swiat::getSzerokosc()
{
    return szerokosc;
}

int Swiat::getWysokosc()
{
    return wysokosc;
}
