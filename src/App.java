import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Grafo grafo = new Grafo();
        grafo.addNode("1");
        grafo.addNode("2");
        grafo.addNode("3");
        grafo.addNode("4");
        grafo.addNode("100000");
        grafo.createAdjacencia(0, 1, 20);
        grafo.createAdjacencia(1, 0, 10);
        grafo.createAdjacencia(4, 3, 999);
        grafo.createAdjacencia(3, 1, 5);
        grafo.createAdjacencia(0, 2, 13);
        grafo.removeAdjacencia(4, 3);
        grafo.setNode(4, "5");
        grafo.createAdjacencia(3, 4, 55);
        grafo.printMatrixAd();
        
        String nodePraTestar = "1";
        ArrayList<String> adjacencias = new ArrayList<>();
        int n_adjacencias = grafo.getAdjacencias(nodePraTestar, adjacencias);
        System.out.println("\nO vertice " + nodePraTestar + " tem " + n_adjacencias + " adjacencias!");
        System.out.println("Adjacencias: " + adjacencias);
    }
}
