import java.util.ArrayList;

public class Rozklad {
    private Integer ilePociagow;
    private ArrayList<Pociag> tabelaPociagow;

    public Integer ilePociagow() {
        return tabelaPociagow.size();
    }

    public Pociag najblizszyPociag(){
        return tabelaPociagow.get(0);
    }

    public void wyswietl(){
        // wyświetlanie na ekranie a nie w konsoli raczej
    }
    public void dodaj(Pociag pociag){
        tabelaPociagow.add(pociag);
    }
    public void usunPierwszy(String nazwa){
        tabelaPociagow.remove(0);
    }

    public Rozklad(ArrayList<Pociag> tabelaPociagow) {
        this.tabelaPociagow = tabelaPociagow;
        this.ilePociagow = tabelaPociagow.size();
        this.tabelaPociagow.sort((o1, o2) -> o1.getCzasPrzyjazdu() - o2.getCzasPrzyjazdu());
    }
}
