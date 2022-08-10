import java.util.ArrayList;

public class Grafo {
    ArrayList<String> nodes;
    Matrix matrizAd;
    public Grafo(){
        this.nodes = new ArrayList<>();
        this.matrizAd = new Matrix(0, 0);
    }

    public Grafo(ArrayList<String> nodes){
        this.nodes = nodes;
        this.matrizAd = new Matrix(nodes.size(), nodes.size());
    }
    public void addNode(String node){
        nodes.add(node);
        this.matrizAd.addLineColumn(1, 1);
        this.matrizAd.setLabelColumn(this.matrizAd.getheight() - 1, node);
        this.matrizAd.setLabelLine(this.matrizAd.getWidth() - 1, node);
        this.matrizAd.setValue(this.matrizAd.getWidth() - 1, this.matrizAd.getheight() - 1, -1);
    }
    public void createAdjacencia(String node1, String node2, int peso){
        matrizAd.setValueFromLabel(node1, node2, peso);
    }
    public void removeAdjacencia(String node1, String node2){
        matrizAd.setValueFromLabel(node1, node2, 0);
    }
    public String getNode(int i){
        return nodes.get(i);
    }
    public void printMatrizAd(){
        this.matrizAd.printMatrix();
    }
}
