import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Grafo grafo = new Grafo();
        grafo.addNode("1");
        grafo.addNode("2");
        grafo.addNode("3");
        grafo.addNode("4");
        grafo.addNode("100000");
        grafo.createAdjacencia("1", "2", 20);
        grafo.createAdjacencia("2", "1", 10);
        grafo.createAdjacencia("100000", "4", 999);
        grafo.createAdjacencia("4", "2", 5);
        grafo.createAdjacencia("1", "3", 13);
        grafo.removeAdjacencia("100000", "4");
        grafo.setNode("100000", "5");
        grafo.createAdjacencia("4", "5", 55);
        grafo.printMatrizAd();
        
        String nodePraTestar = "1";
        ArrayList<String> adjacencias = new ArrayList<>();
        int n_adjacencias = grafo.getAdjacencias(nodePraTestar, adjacencias);
        System.out.println("\nO vertice " + nodePraTestar + " tem " + n_adjacencias + " adjacencias!");
        System.out.println("Adjacencias: " + adjacencias);
    }
}
