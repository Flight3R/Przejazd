import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Swiatlo sygnalizacjaTestowaP = new Swiatlo(new Polozenie(1, -4), "sygnalizacja_pas_prawy");
        Rogatka rogatkaTestowaP = new Rogatka(new Polozenie(1, -3), "rogatka_pas_prawy", 5);
        PasRuchu pasTestowyP = new PasRuchu(new Polozenie(1, 0), "pas_prawy", sygnalizacjaTestowaP, "gora", 1000, rogatkaTestowaP);
        Swiatlo sspTestoweD = new Swiatlo(new Polozenie(-1500, -2), "SSP_tor_dolny");
        Czujnik czujnikTestowy1D = new Czujnik(new Polozenie(-1800, -2), "czujnik_przed_tor_dolny");
        Czujnik czujnikTestowy2D = new Czujnik(new Polozenie(10, -2), "czujnik_za_tor_dolny");
        Tor torTestowyD = new Tor(new Polozenie(0, -2), "tor_dolny", sspTestoweD, "prawo", 5000, czujnikTestowy1D, czujnikTestowy2D);

        Swiatlo sygnalizacjaTestowaL = new Swiatlo(new Polozenie(-1, 4), "sygnalizacja_pas_lewy");
        Rogatka rogatkaTestowaL = new Rogatka(new Polozenie(-1, 3), "rogatka_pas_lewy", 5);
        PasRuchu pasTestowyL = new PasRuchu(new Polozenie(-1, 0), "pas_lewy", sygnalizacjaTestowaL, "dol", 1000, rogatkaTestowaL);
        Swiatlo sspTestoweG = new Swiatlo(new Polozenie(1500, 2), "SSP_tor_gorny");
        Czujnik czujnikTestowy1G = new Czujnik(new Polozenie(1800, 2), "czujnik_przed_tor_gorny");
        Czujnik czujnikTestowy2G = new Czujnik(new Polozenie(-10, 2), "czujnik_za_tor_gorny");
        Tor torTestowyG = new Tor(new Polozenie(0, 2), "tor_dolny", sspTestoweG, "lewo", 5000, czujnikTestowy1G, czujnikTestowy2G);
        Przejazd przejazdTestowy = new Przejazd(new Polozenie(0,0), "przejazd",pasTestowyL, pasTestowyP,torTestowyG, torTestowyD,null, 0);

        Pociag pociagTestowy = new Pociag(120,30000,27.67,new Polozenie(-2500,-2),"112200",0, torTestowyD, przejazdTestowy);

    }
}
