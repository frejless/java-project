public class Hodnotenie {

    private  String komentar;
    private  int hodnotenie;

    public Hodnotenie(int hodnotenie, String komentar) {
        this.hodnotenie = hodnotenie;
        this.komentar = komentar;
    }

    public  String getKomentar() {
        if (komentar == null) {
            return "";
        }
        else {
            return komentar;
        }
    }
    public  int getHodnotenie() {
        return hodnotenie;
    }


}
