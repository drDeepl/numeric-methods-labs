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
}