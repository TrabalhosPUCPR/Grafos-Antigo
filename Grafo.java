import java.util.ArrayList;

public class Grafo {
    ArrayList<Node<?>> nodes;
    ArrayList<ArrayList<Integer>> matrizAd;
    public Grafo(){
        this.nodes = new ArrayList<>();
        this.matrizAd = new ArrayList<>();
        matrizAd.add(new ArrayList<>());
        matrizAd.add(new ArrayList<>());
    }
    public Grafo(ArrayList<Node<?>> nodes){
        this.nodes = nodes;
        this.matrizAd = new ArrayList<>();
        matrizAd.add(new ArrayList<>());
        matrizAd.add(new ArrayList<>());
    }
    public void addNode(Node<?> node){
        nodes.add(node);
        matrizAd.get(0).add(0);
        matrizAd.get(1).add(0);
    }
    public void createAdjacencia(Node<?> node1, Node<?> node2, int peso){
        matrizAd.get(0).set(nodes.indexOf(node1), peso);
        matrizAd.get(1).set(nodes.indexOf(node2), peso);
    }
    public void removeAdjacencia(Node<?> node1, Node<?> node2){
        matrizAd.get(0).set(nodes.indexOf(node1), 0);
        matrizAd.get(1).set(nodes.indexOf(node2), 0);
    }
    public void setRotulo(Node<?> node, String v){
        nodes.get(nodes.indexOf(node)).setRotulo(v);
    }
}
