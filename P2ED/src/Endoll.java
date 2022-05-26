public class Endoll {
    /*
        exemple:
                "id": "20106805",
                "nom": "TEST - INGETEAM EdRV 1",
                "data": "2021-07-28 11:09:12",
                "consum": "",
                "carrer": "ETECNIC Energy & Mobility",
                "ciutat": "Reus",
                "estat": "lliure",
                "temps": "0",
                "potencia": "22",
                "tipus": "CHAdeMO (DC)",
                "latitud": "41.155346",
                "longitud": "1.097065"
         */
    private int id;
    private String nom, data, consum, carrer, ciutat, estat, temps, potencia, tipus;

    public Endoll(int id, String nom, String data, String consum, String carrer, String ciutat, String estat, String temps, String potencia, String tipus) {
        this.id = id;
        this.nom = nom;
        this.data = data;
        this.consum = consum;
        this.carrer = carrer;
        this.ciutat = ciutat;
        this.estat = estat;
        this.temps = temps;
        this.potencia = potencia;
        this.tipus = tipus;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getData() {
        return data;
    }

    public String getConsum() {
        return consum;
    }

    public String getCarrer() {
        return carrer;
    }

    public String getCiutat() {
        return ciutat;
    }

    public String getEstat() {
        return estat;
    }

    public String getTemps() {
        return temps;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public String getTipus() {
        return tipus;
    }
}
