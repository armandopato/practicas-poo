import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Representa una matriz.
 */
public class Matrix
{
    /**
     * Número de hilos a utilizar al realizar la operación de suma.
     */
    private static final int NUMBER_OF_THREADS = 4;
    
    /**
     * Representación interna de ésta matriz por un arreglo bidimensional.
     */
    private final int[][] representation;
    
    /**
     * Construye una nueva matriz a partir de una representación en una lista bidimensional.
     *
     * @param representation Representación de la matriz.
     */
    public Matrix(List<List<Integer>> representation)
    {
        int rows = representation.size();
        int cols = representation.get(0).size();
        this.representation = new int[rows][cols];
        for (int i = 0; i < rows; i++)
        {
            this.representation[i] = representation.get(i).stream().mapToInt(Integer::intValue).toArray();
        }
    }
    
    /**
     * Construye una matriz vacía, con las dimensiones especificadas.
     *
     * @param rows Número de filas.
     * @param cols Número de columnas.
     */
    private Matrix(int rows, int cols)
    {
        this.representation = new int[rows][cols];
    }
    
    /**
     * Suma los elementos presentes en la región indicada de dos matrices representadas por arreglos bidimensionales, y
     * coloca el resultado en las posiciones respectivas en la matriz <code>result</code>.
     *
     * @param matrix1 Matriz a sumar.
     * @param matrix2 Matriz a sumar.
     * @param result  Matriz donde se colocan los resultados.
     * @param startX  Posición de la primera fila a sumar, con índices basados en 0.
     * @param endX    Posición de la última fila a sumar, con índices basados en 0.
     * @param startY  Posición de la primera columna a sumar, con índices basados en 0.
     * @param endY    Posición de la última columna a sumar, con índices basados en 0.
     */
    private static void sumQuadrant(int[][] matrix1, int[][] matrix2, int[][] result, int startX, int endX, int startY,
                                    int endY)
    {
        for (int i = startX; i <= endX; i++)
        {
            for (int j = startY; j <= endY; j++)
            {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
    }
    
    /**
     * Computa la suma entre el cuadrante superior izquierdo de dos matrices. Coloca los resultados en las posiciones
     * respectivas en la matriz <code>result</code>.
     *
     * @param matrix1 Matriz a sumar.
     * @param matrix2 Matriz a sumar.
     * @param result  Matriz donde se colocan los resultados.
     */
    private static void sumFirstQuadrant(Matrix matrix1, Matrix matrix2, Matrix result)
    {
        sumQuadrant(matrix1.getInternalRepresentation(),
                matrix2.getInternalRepresentation(),
                result.getInternalRepresentation(),
                0,
                matrix1.getNumberOfRows() / 2,
                0,
                matrix1.getNumberOfColumns() / 2);
    }
    
    /**
     * Computa la suma entre el cuadrante superior derecho de dos matrices. Coloca los resultados en las posiciones
     * respectivas en la matriz <code>result</code>.
     *
     * @param matrix1 Matriz a sumar.
     * @param matrix2 Matriz a sumar.
     * @param result  Matriz donde se colocan los resultados.
     */
    private static void sumSecondQuadrant(Matrix matrix1, Matrix matrix2, Matrix result)
    {
        sumQuadrant(matrix1.getInternalRepresentation(),
                matrix2.getInternalRepresentation(),
                result.getInternalRepresentation(),
                0,
                matrix1.getNumberOfRows() / 2,
                (matrix1.getNumberOfColumns() / 2) + 1,
                matrix1.getNumberOfColumns() - 1);
    }
    
    /**
     * Computa la suma entre el cuadrante inferior izquierdo de dos matrices. Coloca los resultados en las posiciones
     * respectivas en la matriz <code>result</code>.
     *
     * @param matrix1 Matriz a sumar.
     * @param matrix2 Matriz a sumar.
     * @param result  Matriz donde se colocan los resultados.
     */
    private static void sumThirdQuadrant(Matrix matrix1, Matrix matrix2, Matrix result)
    {
        sumQuadrant(matrix1.getInternalRepresentation(),
                matrix2.getInternalRepresentation(),
                result.getInternalRepresentation(),
                (matrix1.getNumberOfRows() / 2) + 1,
                matrix1.getNumberOfRows() - 1,
                0,
                matrix1.getNumberOfColumns() / 2);
    }
    
    /**
     * Computa la suma entre el cuadrante inferior derecho de dos matrices. Coloca los resultados en las posiciones
     * respectivas en la matriz <code>result</code>.
     *
     * @param matrix1 Matriz a sumar.
     * @param matrix2 Matriz a sumar.
     * @param result  Matriz donde se colocan los resultados.
     */
    private static void sumFourthQuadrant(Matrix matrix1, Matrix matrix2, Matrix result)
    {
        sumQuadrant(matrix1.getInternalRepresentation(),
                matrix2.getInternalRepresentation(),
                result.getInternalRepresentation(),
                (matrix1.getNumberOfRows() / 2) + 1,
                matrix1.getNumberOfRows() - 1,
                (matrix1.getNumberOfColumns() / 2) + 1,
                matrix1.getNumberOfColumns() - 1);
    }
    
    /**
     * Analiza sintácticamente la entrada presente en scanner, y, si ésta tiene una representación válida de una matriz,
     * retorna una <code>Matrix</code> que la representa.
     *
     * @param scanner El Scanner a utilizar.
     * @return Una instancia de <code>Matrix</code> que representa los datos de entrada.
     */
    public static Matrix parseMatrix(Scanner scanner)
    {
        List<List<Integer>> matrixRepresentation = new ArrayList<>();
        while (scanner.hasNextLine())
        {
            String currentLine = scanner.nextLine();
            if (currentLine.equals("")) break;
            String[] numbers = currentLine.split(" ");
            List<Integer> row = Arrays.stream(numbers)
                    .filter(Predicate.not(String::isBlank))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            matrixRepresentation.add(row);
        }
        return new Matrix(matrixRepresentation);
    }
    
    /**
     * Obtiene la representación interna de esta matriz. Es decir, un arreglo bidimensional.
     *
     * @return La representación interna de esta matriz.
     */
    private int[][] getInternalRepresentation()
    {
        return this.representation;
    }
    
    /**
     * Obtiene el número de filas de esta matriz.
     *
     * @return El número de filas de esta matriz.
     */
    public int getNumberOfRows()
    {
        return this.representation.length;
    }
    
    /**
     * Obtiene el número de columnas de esta matriz.
     *
     * @return El número de columnas de esta matriz.
     */
    public int getNumberOfColumns()
    {
        if (this.getNumberOfRows() == 0) return 0;
        return this.representation[0].length;
    }
    
    /**
     * Obtiene la suma entre esta matriz y la matriz <code>other</code> proporcionada. Utiliza un hilo para realizar las
     * sumas de cada uno de los 4 cuadrantes.
     *
     * @param other La matriz a sumar.
     * @return Una nueva matriz que contiene el resultado de la suma.
     */
    public Matrix sum(Matrix other)
    {
        if (this.getNumberOfRows() != other.getNumberOfRows() || this.getNumberOfColumns() != other
                .getNumberOfColumns())
        {
            throw new IllegalArgumentException("Matrices should contain the same number of rows and columns");
        }
        
        Matrix result = new Matrix(this.getNumberOfRows(), this.getNumberOfColumns());
        Thread[] threads = new Thread[NUMBER_OF_THREADS];
        
        threads[0] = new Thread(() -> Matrix.sumFirstQuadrant(this, other, result));
        threads[1] = new Thread(() -> Matrix.sumSecondQuadrant(this, other, result));
        threads[2] = new Thread(() -> Matrix.sumThirdQuadrant(this, other, result));
        threads[3] = new Thread(() -> Matrix.sumFourthQuadrant(this, other, result));
        
        for (int i = 0; i < NUMBER_OF_THREADS; i++)
        {
            threads[i].start();
        }
        
        for (int i = 0; i < NUMBER_OF_THREADS; i++)
        {
            try
            {
                threads[i].join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * Obtiene la representación de esta matriz como cadena. Cada fila de la matriz se representa como una línea, y los
     * elementos se encuentran separados por espacios.
     *
     * @return La representación de esta matriz como cadena.
     */
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.getNumberOfRows(); i++)
        {
            for (int j = 0; j < this.getNumberOfColumns(); j++)
            {
                stringBuilder.append(this.representation[i][j]).append(" ");
            }
            stringBuilder.replace(stringBuilder.length() - 1, Integer.MAX_VALUE, "\n");
        }
        return stringBuilder.toString();
    }
}
