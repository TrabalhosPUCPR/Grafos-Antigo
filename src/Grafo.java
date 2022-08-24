import java.util.ArrayList;

public class Grafo {
    ArrayList<String> nodes;
    Matrix matrixAd;
    public Grafo(){
        this.nodes = new ArrayList<>();
        this.matrixAd = new Matrix(0, 0);
    }
    public Grafo(ArrayList<String> nodes){
        this.nodes = nodes;
        this.matrixAd = new Matrix(nodes.size(), nodes.size());
    }
    public void addNode(String node){
        nodes.add(node);
        this.matrixAd.addLineColumn(1, 1);
        this.matrixAd.setLabelColumn(this.matrixAd.getheight() - 1, node);
        this.matrixAd.setLabelLine(this.matrixAd.getWidth() - 1, node);
        this.matrixAd.setValue(this.matrixAd.getWidth() - 1, this.matrixAd.getheight() - 1, Double.POSITIVE_INFINITY);
    }
    public String getNode(int i){
        return nodes.get(i);
    }
    public void setNode(String node, String newNode) {
    	for(int i = 0; i < nodes.size(); i++) {
    		if(nodes.get(i).equals(node)) {
    			matrixAd.setLabelColumn(i, newNode);
    			matrixAd.setLabelLine(i, newNode);
    		}
    	}
    }
    public void setNode(int node, String newNode) {
        matrixAd.setLabelColumn(node, newNode);
        matrixAd.setLabelLine(node, newNode);
    }
    public void createAdjacencia(String node1, String node2, int peso){
        matrixAd.setValueFromLabel(node1, node2, peso);
    }
    public void removeAdjacencia(String node1, String node2){
        matrixAd.setValueFromLabel(node1, node2, Double.POSITIVE_INFINITY);
    }
    public void createAdjacencia(int node1, int node2, int peso){
        matrixAd.setValue(node1, node2, peso);
    }
    public void removeAdjacencia(int node1, int node2){
        matrixAd.setValue(node1, node2, Double.POSITIVE_INFINITY);
    }
    public int getAdjacencias(int node, ArrayList<String> adjacentes) {
        int n = 0;
        double[] temp = matrixAd.getLine(node);
        for(int i = 0; i < temp.length; i++) {
            if(temp[i] != Double.POSITIVE_INFINITY) {
                adjacentes.add(nodes.get(i));
                n++;
            }
        }
        temp = matrixAd.getColumn(node);
        for(int i = 0; i < temp.length; i++) {
            if(temp[i] != Double.POSITIVE_INFINITY && !adjacentes.contains(nodes.get(i))) {
                adjacentes.add(nodes.get(i));
                n++;
            }
        }
        return n;
    }
    public int getAdjacencias(String node, ArrayList<String> adjacentes) {
        return getAdjacencias(getIndex(node), adjacentes);
    }
    public int getIndex(String node){
        for(int i = 0; i < nodes.size(); i++){
            if(nodes.get(i).equals(node)){
                return i;
            }
        }
        return -1;
    }
    public void printMatrixAd(){
        this.matrixAd.printMatrix();
    }
    public boolean[][] getTransitiveClosureMatrix(){
        double[][] matrix = this.matrixAd.getMatrix();
        boolean[][] closure = new boolean[matrix.length][matrix.length];
        //System.arraycopy(this.matrix, 0, closure, 0, this.matrix.length-1);

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                closure[i][j] = matrix[i][j] != Double.POSITIVE_INFINITY;
            }
        }
        for(int k = 0; k < matrix.length; k++){
            for (int i = 0; i < matrix.length; i++){
                if(closure[i][k]){
                    for(int j = 0; j < matrix.length; j++){
                        closure[i][j] = closure[i][j] || closure[k][j];
                    }
                }
            }
        }
        return closure;
    }
}
