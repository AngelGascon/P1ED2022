public class Estacio {
    /*
    exemple:
            "id": "20106805",
            "id_estacio": "35664709",
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
    private String idEstacio, nom, data, consum, carrer, ciutat, estat, temps, potencia, tipus;
    private double latitud, longitud;

    public Estacio(int id, String idEstacio, String nom, String data, String consum, String carrer, String ciutat, String estat, String temps, String potencia, String tipus, double latitud, double longitud) {
        this.id = id;
        this.idEstacio = idEstacio;
        this.nom = nom;
        this.data = data;
        this.consum = consum;
        this.carrer = carrer;
        this.ciutat = ciutat;
        this.estat = estat;
        this.temps = temps;
        this.potencia = potencia;
        this.tipus = tipus;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getId() {
        return id;
    }

    public String getIdEstacio() {
        return idEstacio;
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

    public String getTipus() {
        return tipus;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}
