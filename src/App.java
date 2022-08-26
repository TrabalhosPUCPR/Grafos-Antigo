import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Grafo grafo = new Grafo();
        // /*
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
        /* OUTROS EXEMPLOS
        grafo.addNode("1");
        grafo.addNode("2");
        grafo.addNode("3");
        grafo.addNode("4");
        grafo.addNode("5");
        grafo.addNode("6");
        grafo.addNode("7");
        grafo.addNode("8");

         EX 1
        grafo.createAdjacenciaNaoDirecionado("1", "2", 2);
        grafo.createAdjacenciaNaoDirecionado("1", "6", 2);
        grafo.createAdjacenciaNaoDirecionado("1", "5", 3);
        grafo.createAdjacenciaNaoDirecionado("1", "3", 5);
        grafo.createAdjacenciaNaoDirecionado("2", "7", 1);
        grafo.createAdjacenciaNaoDirecionado("2", "3", 2);
        grafo.createAdjacenciaNaoDirecionado("6", "7", 5);
        grafo.createAdjacenciaNaoDirecionado("6", "5", 6);
        grafo.createAdjacenciaNaoDirecionado("7", "8", 1);
        grafo.createAdjacenciaNaoDirecionado("8", "3", 2);
        grafo.createAdjacenciaNaoDirecionado("8", "5", 1);
        grafo.createAdjacenciaNaoDirecionado("5", "4", 4);
        grafo.createAdjacenciaNaoDirecionado("3", "4", 1);

        EX 2
        grafo.createAdjacencia("1", "2", 2);
        grafo.createAdjacencia("1", "6", 2);
        grafo.createAdjacencia("1", "5", 3);
        grafo.createAdjacencia("2", "3", 1);
        grafo.createAdjacencia("5", "3", 1);
        grafo.createAdjacencia("5", "6", 1);
        grafo.createAdjacencia("5", "7", 2);
        grafo.createAdjacencia("3", "4", 1);
        grafo.createAdjacencia("4", "5", 1);
        grafo.createAdjacencia("4", "7", 1);
        grafo.createAdjacencia("6", "7", 3);
         */

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
