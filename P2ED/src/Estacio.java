import java.util.LinkedList;

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
    private String idEstacio;
    private LinkedList<Endoll> endolls;
    private Endoll maxPotencia;
    private double latitud, longitud;

    public Estacio(int id, String idEstacio, String nom, String data, String consum, String carrer, String ciutat, String estat, String temps, String potencia, String tipus, double latitud, double longitud) {
        this.idEstacio = idEstacio;
        this.latitud = latitud;
        this.longitud = longitud;
        endolls = new LinkedList<>();
        maxPotencia = new Endoll(id, nom, data, consum, carrer, ciutat, estat, temps, potencia, tipus);
        addEndoll(maxPotencia);
    }

    public void addEndoll(Endoll e){
        endolls.add(e);
        if(e.getPotencia().compareTo("")==0) e.setPotencia("0");
        if(Double.parseDouble(maxPotencia.getPotencia())<Double.parseDouble(e.getPotencia()))
            maxPotencia = e;
    }

    public String getIdEstacio() {
        return idEstacio;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public String toString(){
        return "Estacio: "+idEstacio +" || Endoll: "+ maxPotencia.getId()+" ("+maxPotencia.getPotencia()+"V)";
    }
}
