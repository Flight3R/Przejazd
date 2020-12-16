public class Czujnik extends ElementInfrastruktury{
    private Integer zadzialania;

    public void aktywuj(){
            this.zadzialania = this.zadzialania + 1;
    }

    public Integer getZadzialania() {
        return zadzialania;
    }
}
