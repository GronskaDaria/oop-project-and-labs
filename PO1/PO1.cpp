#include <conio.h>
#include <windows.h>
#include <time.h>
#include "Swiat.h"
using namespace std;

enum klawisze {  enter = 13 };

int main()
{
    srand(time(NULL));
    SetConsoleTitle(L"Daria Gronska [191565]");
    SetConsoleOutputCP(CP_UTF8);
    CONSOLE_FONT_INFOEX cfi;
    cfi.cbSize = sizeof(cfi);
    cfi.nFont = 0;
    cfi.dwFontSize.X = 15;
    cfi.dwFontSize.Y = 20;
    cfi.FontFamily = FF_DONTCARE;
    cfi.FontWeight = FW_NORMAL;
    wcscpy_s(cfi.FaceName, L"Courier New");
    SetCurrentConsoleFontEx(GetStdHandle(STD_OUTPUT_HANDLE), FALSE, &cfi);
    CONSOLE_CURSOR_INFO cursorInfo;
    GetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &cursorInfo);
    ios::sync_with_stdio(false);
    int wysokosc, szerokosc;
    cout << u8"Wysokość świata: ";
    cin >> wysokosc;
    cout <<u8"Szerokość świata: ";
    cin >>szerokosc;
    cursorInfo.bVisible = false;
    SetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &cursorInfo);
    Swiat* swiat = Swiat::getInstance(szerokosc, wysokosc);
    swiat->poczatkowaPopulacja();
    swiat->rysujSwiat();
    char klawisz;
    while (1) {
        klawisz = _getch();
        swiat->wykonajTure();
        swiat->rysujSwiat();
    }
    return 0;
}