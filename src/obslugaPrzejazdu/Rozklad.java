package obslugaPrzejazdu;

import pojazdy.Pociag;

import java.util.ArrayList;

public class Rozklad {
    private final ArrayList<Pociag> tabelaPociagow = new ArrayList<>();

// ------------------ gettery ------------------
    public ArrayList<Pociag> getTabelaPociagow() {
        return tabelaPociagow;
    }

    public Integer getIlePociagow() {
        return tabelaPociagow.size();
    }

    public Pociag getNajblizszyPociag(){
        return tabelaPociagow.get(0);
    }

// ------------------ metody ------------------
    public void dodaj(Pociag pociag){
        if (!tabelaPociagow.contains(pociag)) {
            tabelaPociagow.add(pociag);
            this.tabelaPociagow.sort((p1, p2) -> p1.getCzasPrzyjazdu() - p2.getCzasPrzyjazdu());
        }
    }

    public void usunPierwszy(){
       tabelaPociagow.remove(0);
    }
}
