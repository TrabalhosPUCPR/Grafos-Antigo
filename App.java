public class App {
    public static void main(String[] args) throws Exception {
        Grafo grafo = new Grafo();
        grafo.addNode("1");
        grafo.addNode("2");
        grafo.addNode("3");
        grafo.addNode("4");
        grafo.addNode("100000");
        grafo.createAdjacencia("1", "2", 20);
        grafo.printMatrizAd();
    }
}
