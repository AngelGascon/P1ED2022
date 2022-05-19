import org.json.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;

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

        GrafPuntsCarrega<Integer,Double> grafTest = new GrafPuntsCarrega<>();
        Estacio novaEstacio;
        NodePuntsCarrega<Integer, Double> nouNode;
        NodePuntsCarrega<Integer, Double> nodeDistMin = null;
        ArrayList<NodePuntsCarrega<Integer, Double>> nodesLlista = new ArrayList<>();
        double dist, distMin = Double.MAX_VALUE;

        //Afegim tots els nodes:estacions
        for (Object actual: arrayFile) {
            //Recorre tot el .json -> tot i fer casting a Object tractem en JSONObject
            novaEstacio = new Estacio(((JSONObject) actual).getInt("id"),((JSONObject) actual).getString("id_estacio"),((JSONObject) actual).getString("nom"),((JSONObject) actual).getString("data"),((JSONObject) actual).getString("consum"),((JSONObject) actual).getString("carrer"),((JSONObject) actual).getString("ciutat"),((JSONObject) actual).getString("estat"),((JSONObject) actual).getString("temps"),((JSONObject) actual).getString("potencia"),((JSONObject) actual).getString("tipus"),((JSONObject) actual).getDouble("latitud"),((JSONObject) actual).getDouble("longitud"));
            nouNode = new NodePuntsCarrega<>(novaEstacio, novaEstacio.getId());
            grafTest.afegirNode(novaEstacio.getId(),nouNode);
            nodesLlista.add(nouNode);
        }
        ///Rel arestes
        //Creem les arestes per cada inserció a ser possible
        for (NodePuntsCarrega<Integer, Double> nodeRec:nodesLlista){
            //Afegir aresta si ecuclidiana<40Km
            //sino connectar indexNodeNou en el més proper
            distMin = Double.MAX_VALUE;
            for (int i = 0; i<nodesLlista.size(); i++){
                if(nodeRec.ref != nodesLlista.get(i).ref){
                    dist = haversine(nodeRec.info.getLatitud(), nodeRec.info.getLongitud(), nodesLlista.get(i).info.getLatitud(), nodesLlista.get(i).info.getLongitud());
                    if(dist < 40){
                        grafTest.afegirAresta(nodeRec,nodesLlista.get(i), dist);
                    }else{
                        distMin = dist;
                        nodeDistMin = nodesLlista.get(i);
                    }
                }
            }
            //Si el nodeNou no te cap aresta s'enllaça en nodeDistMin , if(!existeixAresta())
            if(distMin!=Double.MAX_VALUE) grafTest.afegirAresta(nodeRec, nodeDistMin, distMin);
        }
        //Graf construit

        //System.out.println(grafTest.existeixAresta(grafTest.getHashValue(26945076), grafTest.getHashValue(11916675)));
        //System.out.println(grafTest.valorAresta(grafTest.getHashValue(9165), grafTest.getHashValue(11916675)));
        System.out.println(grafTest.camiOptim(9165,10833509,30));
        System.out.println(grafTest.zonesDistMaxNoGarantida(9165, 30));
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
