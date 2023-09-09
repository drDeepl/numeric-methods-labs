package classes

import kotlin.random.Random

class Matrix(private val rows: Int, private val columns: Int){
    private val matrix: Array<DoubleArray> = Array(rows) {DoubleArray(columns)}

    public fun getRows(): Int{
        return matrix.size
    }

    public fun getColumns(): Int{
        return matrix[0].size
    }

    public fun getValue(row: Int, column: Int): Double{
        return matrix[row][column]
    }
    fun genRandomMatrix(){
        println("Создание рандомной действительной матрицы")
        for(n in 0..rows-1){
            var rowMatrix: String = "";
            for(m in 0..columns-1){
                matrix[n][m] = Math.round(Random.nextDouble(0.0, 10.0) * 10.0) / 10.0
            }
        }
    }


    fun printMatrix(){
        for(n in 0..rows-1){
            var rowMatrix: String = "";
            for(m in 0..columns-1){
                print("${matrix[n][m]}\t");
            }
            println();
        }
    }

    fun info(){
        println("Строк:${rows}\nКолонок:${columns}")
    }

    public fun getMatrix(): Array<DoubleArray>{
        return matrix
    }

    public fun setValue(row: Int, column: Int, value: Double){
        matrix[row][column] = Math.round(value * 10.0) / 10.0;
    }

}