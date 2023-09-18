package classes

import kotlin.random.Random

class Matrix(private val rows: Int, private val columns: Int){
//    private val matrix: Array<DoubleArray> = Array(rows) {DoubleArray(columns)}
private val matrix: Array<DoubleArray> = arrayOf( doubleArrayOf(2.0,8.0,4.0,2.0), doubleArrayOf(1.0,9.0,6.0,-1.0), doubleArrayOf(3.0,6.0,-2.0,1.0) )
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

            for(m in 0..columns-1){
                print("${matrix[n][m]}\t");
            }
            println();
        }
    }

    fun printMatrixFree(mat: Array<DoubleArray>){
        val rows: Int = mat.size
        val columns: Int = mat[0].size
        for(n in 0..rows-1){
            for(m in 0..columns-1){
                print("${mat[n][m]}\t");
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

//    public fun substractRows(row: Int, substractedRow:Int){
//        for(column in 0.)
//
//    }

    public fun dotProductOnRow(row: Int, value: Double){
        for(column in 0..matrix[row].size-1){
            val oldValue: Double = matrix[row][column]
            matrix[row][column] = value * oldValue
            println("old: ${oldValue} new: ${matrix[row][column]}")
        }
    }

}