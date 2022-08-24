import java.util.Arrays;

public class Matrix {
    String[] col_labels;
    String[] line_labels;
    int width;
    int height;
    double[][] matrix;
    int cellWidth = 1;

    public Matrix(int width, int height){
        this.width = width;
        this.height = height;
        col_labels = new String[width];
        line_labels = new String[height];
        matrix = new double[width][height];
    }
    public void addColumn(int toAdd){
        width += toAdd;
        double[][] N = new double[width][height];
        String[] colL = new String[width];
        if(matrix.length == 0){
            Arrays.fill(N[0], Double.POSITIVE_INFINITY);
            col_labels = colL;
            matrix = N;
            return;
        }
        for(int y = 0; y < matrix.length; y++){
            N[y] = matrix[y];
            for(int x = 0; x < matrix[0].length; x++){
                colL[x] = col_labels[x];
                N[y][x] = matrix[y][x];
            }
        }
        matrix = N;
        col_labels = colL;
    }

    public void addLineColumn(int toAddLine, int toAddColumn){
        height += toAddColumn;
        width += toAddLine;
        double[][] N = new double[height][width];
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                N[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        String[] colL = new String[width];
        String[] lineL = new String[height];
        if(matrix.length == 0){
            col_labels = colL;
            line_labels = lineL;
            matrix = N;
            return;
        }
        for(int y = 0; y < matrix.length; y++){
            System.arraycopy(matrix[y], 0, N[y], 0, matrix[y].length);
        }
        System.arraycopy(line_labels, 0, colL, 0, width - 1);
        System.arraycopy(line_labels, 0, lineL, 0, height - 1);
        line_labels = lineL;
        col_labels = colL;
        matrix = N;
    }

    public void addLine(int toAdd){
        height += toAdd;
        double[][] N = new double[width][height];
        String[] lineL = new String[height];
        if(matrix.length == 0){
            Arrays.fill(N[0], Double.POSITIVE_INFINITY);
            line_labels = lineL;
            matrix = N;
            return;
        }
        for(int y = 0; y < matrix.length; y++){
            N[y] = matrix[y];
            System.arraycopy(matrix, 0, N, 0, matrix[0].length);
        }
        line_labels = lineL;
        matrix = N;
    }

    public double getValue(int x, int y){
        return matrix[x][y];
    }
    public void setValue(int x, int y, double value){
        if (String.format("%, .0f", value).length() > cellWidth && value != Double.POSITIVE_INFINITY){
            cellWidth = String.format("%, .0f", value).length();
        }
        matrix[x][y] = value;
    }
    public void setValueFromLabel(String labelX, String LabelY, double value){
        for(int i = 0; i < col_labels.length; i++){
            if(col_labels[i].equals(LabelY)){
                for(int j = 0; j < line_labels.length; j++){
                    if(line_labels[j].equals(labelX)){
                        setValue(j, i, value);
                        return;
                    }
                }
            }
        }        
    }

    public void setLabelColumn(int col, String label){
        this.col_labels[col] = label;
        if(cellWidth < label.length()) cellWidth = label.length();
    }
    public void setLabelLine(int line, String label){
        this.line_labels[line] = label;
        if(cellWidth < label.length()) cellWidth = label.length();
    }

    public void printMatrix(){
   
        // o espaco de cada celula é cellWidth + 4, 2 espacos vazio em cada lado pra deixar 
        //mais bonito

        System.out.print("||  " + " ".repeat(cellWidth) + "  ||");

        // 1 for desses por linha, mas esse fica fora pq ele e a primeira linha, so imprime os labels das colunas
        for(int i = 0; i < width; i++){
            // e um print meio pesado mas e pra ficar legal
            System.out.print("  " + build_text(col_labels[i], cellWidth) + "  |");
        }
        System.out.println();
        System.out.print("||--" + "-".repeat(cellWidth) + "--||");
        
        // mais um for so pra deixar separado os labels e os valores
        for(int i = 1; i < width + 1; i++){
            System.out.print("--" + "-".repeat(cellWidth) + "--|");
        }
        System.out.println();
        
        // a partir daqui comeca a imprimir o label da coluna e os valores da matriz
        for(int y = 0; y < height; y++){
            System.out.print("||  " + build_text(line_labels[y], cellWidth) + "  ||");
            for(int x = 0; x < width; x++){
                if (matrix[y][x] == Double.POSITIVE_INFINITY){
                    System.out.print("  " + build_text("-", cellWidth) + "  |");
                }else{
                    System.out.print("  " + build_text(String.format("%, .0f", matrix[y][x]), cellWidth) + "  |");
                }
            }
            System.out.println();
        }
    }

    public int getWidth(){return this.width;}
    public int getheight(){return height;}
    
    public double[] getColumnFromLabel(String label) {
    	for(int i = 0; i < col_labels.length; i++) {
    		if(col_labels[i].equals(label)) {
    			return getColumn(i);
    		}
    	}
    	return new double[0];
    }
    public double[] getColumn(int column) {
        double[] columnValues = new double[width];
    	for(int i = 0; i < width; i++) {
    		columnValues[i] = this.matrix[i][column];
    	}
    	return columnValues;
    }
    
    public double[] getLine(int line) {
        double[] lineValues = new double[height];
        System.arraycopy(this.matrix[line], 0, lineValues, 0, height);
    	return lineValues;
    }
    public double[] getLineFromLabel(String label) {
    	for(int i = 0; i < line_labels.length; i++) {
    		if(line_labels[i].equals(label)) {
    			return getLine(i);
    		}
    	}
    	return new double[0];
    }

    private static String build_text(Object texto, int max){
        StringBuilder tex = new StringBuilder(texto.toString());
        StringBuilder tex2;
        boolean rev = false;
        while(tex.length() < max){
            if(!rev){
                rev = true;
                tex.append(" ");
            }else{
                rev = false;
                tex.insert(0, " ");
            }
        }
        while(tex.length() > max){
            tex2 = new StringBuilder();
            for(int i = 0; i < tex.length() - 1; i++){
                tex2.append(tex.charAt(i));
            }
            tex = new StringBuilder(tex2.toString());
        }
        return tex.toString();
    }

    public static void print2dArray(boolean[][] arr){

        // o espaco de cada celula é cellWidth + 4, 2 espacos vazio em cada lado pra deixar
        //mais bonito

        int cellWidth = 8;
        int width = arr[0].length;

        System.out.print("  " + build_text("Index: ", cellWidth) + "  |");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("  " + build_text(i, cellWidth) + "  |");
        }

        System.out.println();

        // a partir daqui comeca a imprimir o label da coluna e os valores da matriz
        for (int i = 0; i < arr.length; i++) {
            System.out.print("  " + build_text(i, cellWidth) + "  |");
            for (int x = 0; x < width; x++) {
                System.out.print("  " + build_text(arr[i][x], cellWidth) + "  |");
            }
            System.out.println("");
        }
    }

    public double[][] getMatrix(){
        return this.matrix;
    }
}
