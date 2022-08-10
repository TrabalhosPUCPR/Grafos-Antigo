public class Matrix {
    String col_labels[];
    String line_labels[];
    int width;
    int height;
    int matrix[][];

    public Matrix(int width, int height){
        this.width = width;
        this.height = height;
        col_labels = new String[width];
        line_labels = new String[height];
        matrix = new int[width][height];
    }

    public void addColumn(int toAdd){
        width += toAdd;
        int N[][] = new int[width][height];
        String colL[] = new String[width];
        if(matrix.length == 0){
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

    public void addLineColumn(int toAddLine, int toAddCollumn){
        height += toAddCollumn;
        width += toAddLine;
        int N[][] = new int[width][height];
        String colL[] = new String[width];
        String lineL[] = new String[height];
        if(matrix.length == 0){
            col_labels = colL;
            line_labels = lineL;
            matrix = N;
            return;
        }
        for(int y = 0; y < matrix.length; y++){
            colL[y] = col_labels[y];
            for(int x = 0; x < matrix[y].length; x++){
                N[y][x] = matrix[y][x];
            }
        }
        for(int x = 0; x < height - 1; x++){
            lineL[x] = line_labels[x];
        }
        line_labels = lineL;
        col_labels = colL;
        matrix = N;
    }

    public void addLine(int toAdd){
        height += toAdd;
        int N[][] = new int[width][height];
        String lineL[] = new String[height];
        if(matrix.length == 0){
            line_labels = lineL;
            matrix = N;
            return;
        }
        for(int y = 0; y < matrix.length; y++){
            N[y] = matrix[y];
            for(int x = 0; x < matrix[0].length; x++){
                N[x] = matrix[x];
            }
        }
        line_labels = lineL;
        matrix = N;
    }

    public int getValue(int x, int y){
        return matrix[x][y];
    }
    public void setValue(int x, int y, int value){
        matrix[x][y] = value;
    }
    public void setValueFromLabel(String labelX, String LabelY, int value){
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
    }
    public void setLabelLine(int line, String label){
        this.line_labels[line] = label;
    }

    public void printMatrix(){
        // pega o label das colunas que tem o maior tamanho
        int cellWidth = col_labels[0].length();
        for(int i = 0; i < width; i++){
            if(col_labels[i].length() > cellWidth){
                cellWidth = col_labels[i].length();
            }
        }
        // o espaco real vai ser essa variavel + 4, 2 espacos vazio para cada lado pra deixar 
        //mais bonito

        System.out.print("|  " + " ".repeat(cellWidth) + "  |");

        // 1 for desses por linha, mas esse fica fora pq ele e a primeira linha, so imprime os labels das colunas
        for(int i = 0; i < width; i++){
            // e um print meio pesado mas e pra ficar legal
            System.out.print("  " + verTamMax_table(col_labels[i], cellWidth) + "  |");
        }
        System.out.println("");

        // a partir daqui comeca a imprimir o label da coluna e os valores da matriz
        for(int y = 0; y < height; y++){
            System.out.print("|  " + verTamMax_table(line_labels[y], cellWidth) + "  |");
            for(int x = 0; x < width; x++){
                System.out.print("  " + verTamMax_table(matrix[y][x], cellWidth) + "  |");
            }
            System.out.println("");
        }
    }

    public int getWidth(){return this.width;}
    public int getheight(){return this.height;}

    private static String verTamMax_table(Object texto, int max){
        String tex = texto.toString();
        String tex2;
        boolean rev = false;
        while(tex.length() < max){
            if(rev == false){
                rev = true;
                tex = tex + " ";
            }else{
                rev = false;
                tex = " " + tex;
            }
        }
        while(tex.length() > max){
            tex2 = "";
            for(int i = 0; i < tex.length() - 1; i++){
                tex2 += tex.charAt(i); 
            }
            tex = tex2;
        }
        return tex;
    }
}
