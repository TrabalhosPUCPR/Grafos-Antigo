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
    public String getNode(int i){
        return nodes.get(i);
    }
    
    public void setNode(String node, String newNode) {
    	for(int i = 0; i < nodes.size(); i++) {
    		if(nodes.get(i).equals(node)) {
    			matrizAd.setLabelColumn(i, newNode);
    			matrizAd.setLabelLine(i, newNode);
    		}
    	}

    }
    
    public void createAdjacencia(String node1, String node2, int peso){
        matrizAd.setValueFromLabel(node1, node2, peso);
    }
    public void removeAdjacencia(String node1, String node2){
        matrizAd.setValueFromLabel(node1, node2, 0);
    }
    
    public int getAdjacencias(String node, ArrayList<String> adjacentes) {
    	int n = 0;
    	int[] temp = matrizAd.getLineFromLabel(node);
    	for(int i = 0; i < temp.length; i++) {
    		if(temp[i] > 0) {
    			adjacentes.add(nodes.get(i));
    			n++;
    		}
    	}
    	temp = matrizAd.getColumnFromLabel(node);
    	for(int i = 0; i < temp.length; i++) {
    		if(temp[i] > 0 && !adjacentes.contains(nodes.get(i))) {
    			adjacentes.add(nodes.get(i));
    			n++;
    		}
    	}
    	return n;
    }
    public void printMatrizAd(){
        this.matrizAd.printMatrix();
    }
}
