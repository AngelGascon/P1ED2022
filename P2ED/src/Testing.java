import org.json.*;

import java.io.InputStream;

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

        GrafGeneric<Integer,Estacio,Double> grafTest = new GrafGeneric<>();
        Estacio novaEstacio;
        Node<Integer, Estacio, Double> nouNode;
        Node<Integer, Estacio, Double> nodeDistMin = null;
        Integer indexNodeNou = 0;
        double dist, distMin = Double.MAX_VALUE;

        //Afegim tots els nodes:estacions
        for (Object actual: arrayFile) {
            //Recorre tot el .json -> tot i fer casting a Object tractem en JSONObject
            novaEstacio = new Estacio(((JSONObject) actual).getInt("id"),((JSONObject) actual).getString("id_estacio"),((JSONObject) actual).getString("nom"),((JSONObject) actual).getString("data"),((JSONObject) actual).getString("consum"),((JSONObject) actual).getString("carrer"),((JSONObject) actual).getString("ciutat"),((JSONObject) actual).getString("estat"),((JSONObject) actual).getString("temps"),((JSONObject) actual).getString("potencia"),((JSONObject) actual).getString("tipus"),((JSONObject) actual).getDouble("latitud"),((JSONObject) actual).getDouble("longitud"));
            nouNode = new Node<>(novaEstacio, indexNodeNou);
            grafTest.afegirNode(indexNodeNou,nouNode);
            //Creem les arestes per cada inserció a ser possible
            for (Integer y=0; y<indexNodeNou;y++){
                //Afegir aresta si ecuclidiana<40Km
                //sino connectar indexNodeNou en el més proper
                try{
                    dist = euclidia(grafTest.getHashValue(y).info.getLatitud(), grafTest.getHashValue(y).info.getLongitud(), grafTest.getHashValue(indexNodeNou).info.getLatitud(), grafTest.getHashValue(indexNodeNou).info.getLongitud());
                    if(dist<40) {
                        grafTest.afegirAresta(grafTest.getHashValue(y), grafTest.getHashValue(indexNodeNou), dist);
                    }else if(distMin>dist){
                        distMin = dist;
                        nodeDistMin = grafTest.getHashValue(y);
                    }
                }catch(NullPointerException exception){
                    System.out.println(exception);
                }
            }
            //Si el nodeNou no te cap aresta s'enllaça en nodeDistMin , if(!existeixAresta())
            if(grafTest.adjacents(nouNode)==null) grafTest.afegirAresta(grafTest.getHashValue(indexNodeNou), nodeDistMin, distMin);
            indexNodeNou++;
        }
        System.out.println(grafTest.existeixAresta(grafTest.getHashValue(5), grafTest.getHashValue(1)));
        System.out.println(grafTest.valorAresta(grafTest.getHashValue(5), grafTest.getHashValue(1)));
        System.out.println(grafTest.adjacents(grafTest.getHashValue(1)));
    }
    private static double euclidia(double p1x, double p1y, double p2x, double p2y){
        double result, px, py;
        px = (p1x-p2x)*(p1x-p2x);
        py = (p1y-p2y)*(p1y-p2y);
        result = Math.sqrt(px+py);
        return result;
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

        System.out.println("The distance between two lat and long is (Km): " + distance);
        return distance;
    }
    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }
}
