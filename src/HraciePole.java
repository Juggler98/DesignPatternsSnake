///**
// * Trieda HraciePole vytvori dvojrozmerne pole instancii triedy Stvorec. Teda vytvori hraciu plochu pre hadika.
// * Hadik sa po ploche pohybuje prefarbovaním štvorčekov. Červená je hadík, zelená plátno a modrá je jedlo.
// *
// * @author (Adam Beliansky)
// * @version (a version number or a date)
// */
//public class HraciePole {
//    private Stvorec[][] stvorce;
//
//    /**
//     * Vytvori pole.
//     */
//    public HraciePole() {
//        Stvorec stvorec = new Stvorec();
//        int rozmer = stvorec.getRozmer();
//        int strana = stvorec.getStrana();
//        int velkost = rozmer / strana;
//        this.stvorce = new Stvorec[velkost][velkost];
//        for (int y = 0; y < velkost; ++y) {
//            for (int x = 0; x < velkost; ++x) {
//                this.stvorce[x][y] = new Stvorec();
//                this.stvorce[x][y].posunVodorovne(x * strana);
//                this.stvorce[x][y].posunZvisle(y * strana);
//                this.stvorce[x][y].zobraz();
//            }
//        }
//    }
//
//    public Stvorec getStvorec(int x, int y) {
//        return this.stvorce[x][y];
//    }
//
//    /**
//     * Vykresli jedlo.
//     */
//    public void zobrazJedlo(int x, int y) {
//        this.stvorce[x][y].zmenFarbu("blue");
//    }
//
//    /**
//     *  Zobrazi prvy clanok hadika pri jeho vytvarani.
//     */
//    public void zobraz(int x, int y) {
//        this.stvorce[x][y].zmenFarbu("red");
//    }
//
//    /**
//     * Zobrazi naspat farbu platna. Teda skryje vykreslene.
//     */
//    public void skry(int x, int y) {
//        this.stvorce[x][y].zmenFarbu("green");
//    }
//}