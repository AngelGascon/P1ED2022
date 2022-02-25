public class Citizen implements Comparable<Citizen>{

    private String name, secName, id;

    public Citizen(String name, String secName, String id){
        this.name = name;
        this.secName = secName;
        this.id = id;
    }

    public String getId() { return id; }

    @Override
    public String toString() {
        return "Citizen{" +
                "name='" + name + '\'' +
                ", secName='" + secName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public int compareTo(Citizen o) { return this.id.compareTo(o.getId()); }

}
