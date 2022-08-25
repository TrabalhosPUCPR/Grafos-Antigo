import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Grafo grafo = new Grafo();
        grafo.addNode("A");
        grafo.addNode("B");
        grafo.addNode("C");
        grafo.addNode("D");
        grafo.addNode("E");
        grafo.addNode("F");
        grafo.createAdjacencia("A", "B", 2);
        grafo.createAdjacencia("A", "C", 4);
        grafo.createAdjacencia("B", "C", 1);
        grafo.createAdjacencia("C", "E", 3);
        grafo.createAdjacencia("B", "E", 2);
        grafo.createAdjacencia("B", "D", 4);
        grafo.createAdjacencia("E", "F", 2);
        grafo.createAdjacencia("D", "F", 2);
        //grafo.removeAdjacencia(4, 3);
        //grafo.setNode(4, "5");
        grafo.printMatrixAd();
        
        String nodePraTestar = "A";
        ArrayList<String> adjacencias = new ArrayList<>();
        int n_adjacencias = grafo.getAdjacencias(nodePraTestar, adjacencias);
        System.out.println("\nO vertice " + nodePraTestar + " tem " + n_adjacencias + " adjacencias!");
        System.out.println("Adjacencias: " + adjacencias);

        boolean[][] matrizAlcan = grafo.getTransitiveClosureMatrix();
        System.out.println("\nMatriz de Alcancabilidade: ");
        Matrix.print2dArray(matrizAlcan);

        ArrayList<String> menor_caminho = grafo.getShortestPath("A", "F");
        System.out.print("\nCaminho mais curto do A para o F: ");
        System.out.println(menor_caminho);
    }
}
