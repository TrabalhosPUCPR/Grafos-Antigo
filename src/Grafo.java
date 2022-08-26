import java.util.ArrayList;
import java.util.Collections;

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
    public void createAdjacenciaNaoDirecionado(String node1, String node2, int peso){
        matrixAd.setValueFromLabel(node1, node2, peso);
        matrixAd.setValueFromLabel(node2, node1, peso);
    }
    public void removeAdjacencia(String node1, String node2){
        matrixAd.setValueFromLabel(node1, node2, Double.POSITIVE_INFINITY);
    }
    public void createAdjacencia(int node1, int node2, int peso){
        matrixAd.setValue(node1, node2, peso);
    }
    public void createAdjacenciaNaoDirecionado(int node1, int node2, int peso){
        matrixAd.setValue(node1, node2, peso);
        matrixAd.setValue(node2, node1, peso);
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
    private ArrayList<Integer> getAdjacenciesIndex(int node){
        double[] temp = matrixAd.getLine(node);
        ArrayList<Integer> adj = new ArrayList<>();
        for (int i = 0; i < temp.length; i++){
            if(temp[i] != Double.POSITIVE_INFINITY){
                adj.add(i);
            }
        }
        return adj;
    }
    private int getMinVertex(int node){
        ArrayList<Integer> adj = getAdjacenciesIndex(node);
        double lowest = getEdgeWeight(node, adj.get(0));
        int a = adj.get(0);
        for (int i = 1; i < adj.size(); i++){
            double w = getEdgeWeight(node, adj.get(i));
            if (w < lowest){
                lowest = w;
                a = adj.get(i);
            }
        }
        return a;
    }
    public int getIndex(String node){
        for(int i = 0; i < nodes.size(); i++){
            if(nodes.get(i).equals(node)){
                return i;
            }
        }
        return -1;
    }
    public double getEdgeWeight(String node1, String node2){
        return matrixAd.getLine(getIndex(node1))[getIndex(node2)];
    }
    public double getEdgeWeight(int node1, int node2){
        return matrixAd.getLine(node1)[node2];
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
    public ArrayList<String> getShortestPath(String node1, String node2){
        return getShortestPath(getIndex(node1), getIndex(node2));
    }
    public ArrayList<String> getShortestPath(int node1, int node2) {
        if(!getTransitiveClosureMatrix()[node1][node2]){
            return new ArrayList<String>();
        }
        double[] distancia = new double[nodes.size()];
        boolean[] perm = new boolean[nodes.size()];
        int corrente, i, k=node1;
        double menordist, novadist, dc;
        int[] caminho = new int[nodes.size()];

        for(i=0; i < nodes.size(); ++i){
            perm[i] = false;
            distancia[i] = Integer.MAX_VALUE;
            caminho[i] = -1;
        }
        perm[node1] = true;
        distancia[node1] = 0;
        corrente = node1;
        while(corrente != node2){
            menordist = Integer.MAX_VALUE;
            dc = distancia[corrente];
            for(i = 0; i < nodes.size(); i++){
                if(!perm[i]){
                    novadist = dc + matrixAd.getValue(corrente, i);// arco[corrente][i].peso;
                    if(novadist < distancia[i]){
                        distancia[i] = novadist;
                        caminho[i] = corrente;
                    }
                    if(distancia[i] < menordist){
                        menordist = distancia[i];
                        k = i;
                    }
                }
            }
            corrente = k;
            perm[corrente] = true;
        }
        return getCaminho(node1, node2, caminho);
    }
    public ArrayList<String> getCaminho(int node1, int node2, int[] caminho){
        int i = caminho[node2];
        ArrayList<String> cam = new ArrayList<>();
        cam.add(nodes.get(node2));
        while (i != node1){
            cam.add(nodes.get(i));
            i = caminho[i];
        }
        cam.add(nodes.get(i));
        Collections.reverse(cam);
        return cam;
    }
}
