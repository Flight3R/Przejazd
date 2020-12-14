import java.util.ArrayList;

public class Rozklad {
    private Integer ilePociagow;
    private ArrayList<Pociag> tabelaPociagow;

    public Integer getIlePociagow() {
        return ilePociagow;
    }

    public Pociag najblizszyPociag(){
        return tabelaPociagow.get(0);
    }

    public void wyswietl(){

    }
    public void dodaj(){

    }
    public void usun(String nazwa){
        tabelaPociagow.remove(new Pociag().getNazwa() == nazwa);
    }

    public Rozklad(ArrayList<Pociag> tabelaPociagow) {
        this.tabelaPociagow = tabelaPociagow;
        this.ilePociagow = tabelaPociagow.size();
        this.tabelaPociagow.sort((o1, o2) -> o1.getCzasPrzyjazdu() - o2.getCzasPrzyjazdu());
    }
}
