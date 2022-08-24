import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Grafo grafo = new Grafo();
        grafo.addNode("1");
        grafo.addNode("2");
        grafo.addNode("3");
        grafo.addNode("4");
        grafo.addNode("5");
        grafo.createAdjacencia("1", "2", 20);
        grafo.createAdjacencia("2", "4", 10);
        grafo.createAdjacencia("1", "3", 999);
        grafo.createAdjacencia("4", "3", 5);
        grafo.createAdjacencia("5", "4", 13);
        //grafo.removeAdjacencia(4, 3);
        //grafo.setNode(4, "5");
        grafo.printMatrixAd();
        
        String nodePraTestar = "1";
        ArrayList<String> adjacencias = new ArrayList<>();
        int n_adjacencias = grafo.getAdjacencias(nodePraTestar, adjacencias);
        System.out.println("\nO vertice " + nodePraTestar + " tem " + n_adjacencias + " adjacencias!");
        System.out.println("Adjacencias: " + adjacencias);

        boolean[][] matrizAlcan = grafo.getTransitiveClosureMatrix();
        System.out.println("\nMatriz de Alcancabilidade: ");
        Matrix.print2dArray(matrizAlcan);
    }
}
