import java.util.ArrayList;

public class Rozklad {
    private final ArrayList<Pociag> tabelaPociagow = new ArrayList<>();

    public ArrayList<Pociag> getTabelaPociagow() {
        return tabelaPociagow;
    }

    public Integer ilePociagow() {
        return tabelaPociagow.size();
    }
    public Pociag najblizszyPociag(){
        return tabelaPociagow.get(0);
    }

    public void wyswietl(){
        // wyświetlanie na ekranie a nie w konsoli raczej
        for (int i=0; i<ilePociagow(); i++) {
            System.out.println(tabelaPociagow.get(i));
        }
    }

    public void dodaj(Pociag pociag){
        tabelaPociagow.add(pociag);
        this.tabelaPociagow.sort((p1, p2) -> p1.getCzasPrzyjazdu() - p2.getCzasPrzyjazdu());
    }

    public void usunPierwszy(){
       tabelaPociagow.remove(0);
    }
}
