public class Endoll {
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

    public String getCarrer() {
        return carrer;
    }

    public String getEstat() {
        return estat;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }
}
