package classes

class LinearEquation (private var matrix: Matrix, private val matrixRows: Int, private val matrixColumns: Int){


    public fun printSystemLinearEquation(){
        var matrixValue: Array<DoubleArray> = matrix.getMatrix()

        for(row in 1..matrixRows){
            for(column in 1..matrixColumns){


                if(column == matrixColumns){
                    print("= ${matrixValue[row-1][column-1]}")
                }
                else{
                    print("${matrixValue[row-1][column-1]}x${column}")
                    print(" + ")
                }
            }
            println()
        }
    }

    public fun printSolverSLinearE(answers: DoubleArray){
        var matrixValue: Array<DoubleArray> = matrix.getMatrix()

        for(row in 1..matrixRows){
            for(column in 1..matrixColumns-1){


                println(matrixValue[row-1][column-1]*answers[row-1])
            }
            println()
        }
    }

    private fun directStep() {
        for(k in 0..matrixRows-1){
            for(i in k+1..matrixRows-1){
                for(j in 0..matrixColumns-1){
                    if(matrix.getValue(k,i) != 0.0){
                        val newValue: Double = matrix.getValue(i,k)/matrix.getValue(k,k)
                            val cellValue: Double = matrix.getValue(i,j) - (newValue*matrix.getValue(k,j))
                            matrix.setValue(i, j, cellValue)
                    }
                }
            }
        }
    }
    public fun reverseStep(){
        var x: DoubleArray = DoubleArray(matrixRows, ){it * 0.0};
        x[matrixRows-1] = matrix.getValue(matrixRows-1, matrixColumns-1)/matrix.getValue(matrixRows-1, matrixColumns-2)
        println(x[matrixRows-1])
        for(i in matrixRows-2 downTo 0){
            var sum: Double = matrix.getValue(i, matrixRows-1)
            for(j in i+1 downTo 0){
               sum = sum - matrix.getValue(i,j)*x[j]
            }
            x[i] = Math.round(sum/matrix.getValue(i,i) * 10.0) / 10.0
            print("x${i} = ${x[i]}\n")
        }
    }
    public fun solverMethodGauss(){
        directStep()
        println("\nMatrix with one in column\n")
        reverseStep()
        matrix.printMatrix()

    }

    fun solverMethodsJordanGauss(): DoubleArray {
        val rows: Int = matrix.getRows() - 1
        val columns: Int = matrix.getColumns() - 1
        var x: DoubleArray = DoubleArray(rows + 1)
        for (k in 0..rows) {
            val pivot: Double = matrix.getValue(k, k)

            for (i in 0..rows) {
                if (i != k) {
                    var d: Double = matrix.getValue(i, k) / pivot
                    for (j in k..columns) {
                        var a_ij: Double = matrix.getValue(i, j) - (d * matrix.getValue(k, j))
                        matrix.setValue(i, j, a_ij)
                    }
                }
            }
            matrix.printMatrix()
            println()
        }

        for (i in 0..rows) {
            x[i] = matrix.getValue(i, i) / matrix.getValue(i, columns)
        }
        return x
    }

    fun solverLUDecompose(){
        println("\nSolve by LU Decompose\n")
        var l: Array<DoubleArray> = Array(matrix.getRows()){DoubleArray(matrix.getRows())}
        var u: Array<DoubleArray> = Array(matrix.getRows()){DoubleArray(matrix.getRows())}
        val sizeMatrix: Int = matrix.getRows()-1
        for(i in 0..sizeMatrix){
            for(j in 0..sizeMatrix){
             if(j < i){
                 l[j][i] = 0.0
             }
             else{
                 l[j][i] = matrix.getValue(j,i)
                 for(k in 0..<i){
                     l[j][i] = l[j][i] - l[j][k] * u[k][i]
                 }
             }
            }
            for(j in 0..sizeMatrix){
                if(j <i){
                    u[i][j] = 0.0
                }
                else if(j==i){
                    u[i][j] = 1.0
                }
                else{
                    u[i][j] = matrix.getValue(i,j)/l[i][i]
                    for(k in 0..<i){
                        u[i][j] = u[i][j] - ((l[i][k]*u[k][j])/l[i][i])
                    }
                }
            }
        }
        println("\nL matrix\n")
        matrix.printMatrixFree(l)
        println("\nU matrix\n")
        matrix.printMatrixFree(u)

    }

}