import org.json.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Testing {
    public static void main(String[] args) {
        ///Llegint .json en la llibreria Org.json
        String resourceName = "icaen.json";
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
        System.out.println(grafTest.existeixAresta(ids.get(0), ids.get(1)));
        System.out.println(grafTest.existeixAresta(ids.get(0), ids.get(0)));
        System.out.println(grafTest.existeixAresta(ids.get(0), ids.get(2)));
        System.out.println(grafTest.valorAresta(ids.get(0), ids.get(1)));
        System.out.println(grafTest.valorAresta(ids.get(0), ids.get(0)));
        System.out.println(grafTest.valorAresta(ids.get(0), ids.get(2)));


        System.out.println(grafTest.camiOptim("9142", "3386242", 100));
        System.out.println(grafTest.zonesDistMaxNoGarantida("3701832", 300));
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
        Double distance = R * c;

        //System.out.println("The distance between two lat and long is (Km): " + distance);
        return distance;
    }
    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }

    private static double euclidia(double p1x, double p1y, double p2x, double p2y){
        double result, px, py;
        px = (p1x-p2x)*(p1x-p2x);
        py = (p1y-p2y)*(p1y-p2y);
        result = Math.sqrt(px+py);
        return result;
    }
}
