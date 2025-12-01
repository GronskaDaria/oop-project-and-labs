package laboratorium.po2;

import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Swiat {
    private int sizeX;
    private int sizeY;
    private int numerTury;
    private Organizm[][] plansza;
    private ArrayList<Organizm> organizmy;
    private SwiatGUI swiatGUI;

    public Swiat(SwiatGUI swiatGUI) {
        this(0, 0, swiatGUI);
    }

    public Swiat(int sizeX, int sizeY, SwiatGUI swiatGUI) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.numerTury = 0;
        this.plansza = new Organizm[sizeY][sizeX];
        this.organizmy = new ArrayList<>();
        this.swiatGUI = swiatGUI;
    }

    public void GenerujSwiat() {
        Random rand = new Random();
        Organizm.TypOrganizmu[] typyOrganizmow = {
            Organizm.TypOrganizmu.KOKA,
            Organizm.TypOrganizmu.WILK,
            Organizm.TypOrganizmu.OWCA,
            Organizm.TypOrganizmu.JEZ,
           // Organizm.TypOrganizmu.LIS,
            //Organizm.TypOrganizmu.TRAWA,
            Organizm.TypOrganizmu.MLECZ,
            Organizm.TypOrganizmu.ZMIJA
        };
        for (Organizm.TypOrganizmu typ : typyOrganizmow) {
            int x = rand.nextInt(sizeX);
            int y = rand.nextInt(sizeY);
            DodajOrganizm(NowyOrganizm.StworzNowyOrganizm(typ, this, new Punkt(x, y)));
        }
    }

    public void WykonajTure() {
        numerTury++;
        Komentator.DodajKomentarz("NUMER TURY " + numerTury);
        SortujOrganizmy();
        for (int i = 0; i < organizmy.size(); i++) {
            Organizm organizm = organizmy.get(i);
            if (organizm.getTuraUrodzenia() != numerTury && !organizm.getCzyUmarl()) {
                organizm.Akcja();
            }
        }
        organizmy.removeIf(Organizm::getCzyUmarl);
        organizmy.forEach(organizm -> organizm.setCzyRozmnazalSie(false));
    }
     private void SortujOrganizmy() {
        organizmy.sort((o1, o2) -> {
            int compareInicjatywa = Integer.compare(o2.getInicjatywa(), o1.getInicjatywa());
            return compareInicjatywa != 0 ? compareInicjatywa : Integer.compare(o1.getTuraUrodzenia(), o2.getTuraUrodzenia());
        });
    }

    public void ZapiszSwiat(String nameOfFile) {
        nameOfFile += ".txt";
        try (PrintWriter pw = new PrintWriter(new File(nameOfFile))) {
            pw.printf("%d %d %d %n", sizeX, sizeY, numerTury);
            for (Organizm organizm : organizmy) {
                pw.printf("%s %d %d %d %d %b%n",
                          organizm.getTypOrganizmu(),
                          organizm.getPozycja().getX(),
                          organizm.getPozycja().getY(),
                          organizm.getSila(),
                          organizm.getTuraUrodzenia(),
                          organizm.getCzyUmarl());
            }
        } catch (IOException e) {
            System.out.println("Error while saving the world: " + e.getMessage());
        }
    }

    public static Swiat OdtworzSwiat(String nameOfFile) {
        nameOfFile += ".txt";
        File file = new File(nameOfFile);
        if (!file.exists()) {
            System.out.println("Error: The specified save file does not exist.");
            return null;
        }

        try (Scanner scanner = new Scanner(file)) {
            String[] properties = scanner.nextLine().split(" ");
            int sizeX = Integer.parseInt(properties[0]);
            int sizeY = Integer.parseInt(properties[1]);
            Swiat tmpSwiat = new Swiat(sizeX, sizeY, null);
            tmpSwiat.numerTury = Integer.parseInt(properties[2]);

            while (scanner.hasNextLine()) {
                properties = scanner.nextLine().split(" ");
                Organizm.TypOrganizmu typOrganizmu = Organizm.TypOrganizmu.valueOf(properties[0]);
                int x = Integer.parseInt(properties[1]);
                int y = Integer.parseInt(properties[2]);
                Organizm tmpOrganizm = NowyOrganizm.StworzNowyOrganizm(typOrganizmu, tmpSwiat, new Punkt(x, y));
                tmpOrganizm.setSila(Integer.parseInt(properties[3]));
                tmpOrganizm.setTuraUrodzenia(Integer.parseInt(properties[4]));
                tmpOrganizm.setCzyUmarl(Boolean.parseBoolean(properties[5]));
                tmpSwiat.DodajOrganizm(tmpOrganizm);
            }
            return tmpSwiat;
        } catch (IOException e) {
            System.out.println("Error while loading the world: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number format error while loading the world: " + e.getMessage());
        } catch (IllegalArgumentException e) {
             System.out.println("Illegal argument error while loading the world: " + e.getMessage());
        }
        return null;
    }

   

    public Punkt WylosujWolnePole() {
        Random rand = new Random();
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                if (plansza[i][j] == null) {
                    while (true) {
                        int x = rand.nextInt(sizeX);
                        int y = rand.nextInt(sizeY);
                        if (plansza[y][x] == null) return new Punkt(x, y);
                    }
                }
            }
        }
        return new Punkt(-1, -1);
    }

    public boolean isPoleOccupied(Punkt pole) {
        return plansza[pole.getY()][pole.getX()] != null;
    }

    public Organizm CoZnajdujeSieNaPolu(Punkt pole) {
        return plansza[pole.getY()][pole.getX()];
    }

    public void DodajOrganizm(Organizm organizm) {
        organizmy.add(organizm);
        plansza[organizm.getPozycja().getY()][organizm.getPozycja().getX()] = organizm;
    }

    public void UsunOrganizm(Organizm organizm) {
        plansza[organizm.getPozycja().getY()][organizm.getPozycja().getX()] = null;
        organizm.setCzyUmarl(true);
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getNumerTury() {
        return numerTury;
    }

    public Organizm[][] getPlansza() {
        return plansza;
    }

    public ArrayList<Organizm> getOrganizmy() {
        return organizmy;
    }

    public SwiatGUI getSwiatGUI() {
        return swiatGUI;
    }

    public void setSwiatGUI(SwiatGUI swiatGUI) {
        this.swiatGUI = swiatGUI;
    }
}

