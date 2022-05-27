import org.json.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;

public class TestingPetit {
    public static void main(String[] args) {
        ///Llegint .json en la llibreria Org.json
        String resourceName = "icaenPetit.json";
        InputStream is = Testing.class.getResourceAsStream(resourceName);
        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + resourceName);
        }
        JSONTokener tokener = new JSONTokener(is);
        JSONArray arrayFile = new JSONArray(tokener);

        GrafGeneric<String, Estacio, Double> grafTest = new GrafGeneric<>();
        Hashtable<String, Estacio> estacions = new Hashtable<>();
        ArrayList<String> ids = new ArrayList<>();

        //treiem tota la info del .json i creem estacions
        for (Object actual: arrayFile) {
            //Recorre tot el .json -> tot i fer casting a Object tractem en JSONObject
            if(estacions.containsKey(((JSONObject) actual).getString("id_estacio"))){
                //if estacio exists -> add endoll
                estacions.get(((JSONObject) actual).getString("id_estacio")).addEndoll(new Endoll(((JSONObject) actual).getInt("id"),((JSONObject) actual).getString("nom"),((JSONObject) actual).getString("data"),((JSONObject) actual).getString("consum"),((JSONObject) actual).getString("carrer"),((JSONObject) actual).getString("ciutat"),((JSONObject) actual).getString("estat"),((JSONObject) actual).getString("temps"),((JSONObject) actual).getString("potencia"),((JSONObject) actual).getString("tipus")));
            }else{
                estacions.put(((JSONObject) actual).getString("id_estacio"), new Estacio(((JSONObject) actual).getInt("id"),((JSONObject) actual).getString("id_estacio"),((JSONObject) actual).getString("nom"),((JSONObject) actual).getString("data"),((JSONObject) actual).getString("consum"),((JSONObject) actual).getString("carrer"),((JSONObject) actual).getString("ciutat"),((JSONObject) actual).getString("estat"),((JSONObject) actual).getString("temps"),((JSONObject) actual).getString("potencia"),((JSONObject) actual).getString("tipus"),((JSONObject) actual).getDouble("latitud"),((JSONObject) actual).getDouble("longitud")));
                ids.add(((JSONObject) actual).getString("id_estacio"));
            }
        }
        //establim nodes a la hashlist del graf
        for (String idActual : ids) {
            grafTest.afegirNode(estacions.get(idActual).getIdEstacio(), estacions.get(idActual));
        }
        Double distMin, dist;
        int nodeMin;
        //establim arestes del graf
        for (int i = 0; i<ids.size(); i++){
            nodeMin=-1;
            distMin=Double.MAX_VALUE;
            for (int n = i+1; n<ids.size(); n++){
                dist = haversine(grafTest.getHashValue(ids.get(i)).info.getLatitud(), grafTest.getHashValue(ids.get(i)).info.getLongitud(),
                        grafTest.getHashValue(ids.get(n)).info.getLatitud(), grafTest.getHashValue(ids.get(n)).info.getLongitud());
                if(dist<40.0){
                    grafTest.afegirAresta(estacions.get(ids.get(i)).getIdEstacio(), estacions.get(ids.get(n)).getIdEstacio(), dist);
                }else{
                    distMin = dist;
                    nodeMin=n;
                }
            }
            //si no te arestes veïnes inserir dist min
            if(nodeMin!=-1 && grafTest.adjacents(ids.get(i)).size()==0)
                grafTest.afegirAresta(ids.get(i), ids.get(nodeMin), distMin);
        }

        //--------Testing--------//
        System.out.println("Nodes, id_estacio:"+ids);
        //Existeix aresta:
        System.out.println("ExisteixAresta: ");
        System.out.println("------------------------------------");
        System.out.println("Existeix aresta entre: 9142, 36095402 -> resultat esperat false: "+grafTest.existeixAresta(ids.get(0), ids.get(1)));
        System.out.println("Existeix aresta entre: 9142, 18510906 -> resultat esperat true: "+grafTest.existeixAresta(ids.get(0), ids.get(3)));
        System.out.println("Existeix aresta entre: 18510906, 18510906 -> resultat esperat false: "+grafTest.existeixAresta(ids.get(3), ids.get(3)));
        System.out.println("Existeix aresta entre: 18510906, 36096716 -> resultat esperat true: "+grafTest.existeixAresta(ids.get(3), ids.get(2)));
        //Valor aresta:
        System.out.println("ValorAresta:");
        System.out.println("------------------------------------");
        System.out.println("Valor aresta entre: 18510906, 36096716 -> resultat esperat 7.10: "+grafTest.valorAresta(ids.get(3), ids.get(2)));
        System.out.println("Valor aresta entre: 18510906, 9142 -> resultat esperat 82.67: "+grafTest.valorAresta(ids.get(3), ids.get(0)));
        System.out.println("Valor aresta entre: 9142, 18510906 -> resultat esperat 82.67: "+grafTest.valorAresta(ids.get(0), ids.get(3)));
        System.out.println("Valor aresta entre: 9142, 36095402 -> resultat esperat null: "+grafTest.valorAresta(ids.get(0), ids.get(1)));
        //CamiOptim
        System.out.println("Cami optim:");
        System.out.println("------------------------------------");
        System.out.println("Resultat esperat (de 9142 a 36095402): 9142-> 18510906, recarrega!-> 36095402\n\t"+grafTest.camiOptim("9142", "36095402", 83));
        System.out.println("Resultat esperat: []\n\t"+grafTest.camiOptim("9142", "36095402", 30));
        System.out.println("Resultat esperat: 18510906 -> 36095402\n\t"+grafTest.camiOptim("18510906", "36095402", 8));
        //DistMaxGarantida
        System.out.println("DistMax garantida:");
        System.out.println("------------------------------------");
        System.out.println(grafTest.zonesDistMaxNoGarantida("9142", 83));
        System.out.println(grafTest.zonesDistMaxNoGarantida("9142", 90));
        System.out.println(grafTest.zonesDistMaxNoGarantida("9142", 30));
        System.out.println(grafTest.zonesDistMaxNoGarantida("36095402", 8));
    }

    /**
     * This is the implementation Haversine Distance Algorithm between two places
     * R = earth’s radius (mean radius = 6,371km)
     Δlat = lat2− lat1
     Δlong = long2− long1
     a = sin²(Δlat/2) + cos(lat1).cos(lat2).sin²(Δlong/2)
     c = 2.atan2(√a, √(1−a))
     d = R.c
     *
     */
    private static double haversine(double p1x, double p1y, double p2x, double p2y){
        final int R = 6371; // Radious of the earth
        Double lat1 = p1x;
        Double lon1 = p1y;
        Double lat2 = p2x;
        Double lon2 = p2y;
        Double latDistance = toRad(lat2-lat1);
        Double lonDistance = toRad(lon2-lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        //System.out.println("The distance between two lat and long is (Km): " + distance);
        return R * c;
    }
    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }

    /*private static double euclidia(double p1x, double p1y, double p2x, double p2y){
        double result, px, py;
        px = (p1x-p2x)*(p1x-p2x);
        py = (p1y-p2y)*(p1y-p2y);
        result = Math.sqrt(px+py);
        return result;
    }*/
}