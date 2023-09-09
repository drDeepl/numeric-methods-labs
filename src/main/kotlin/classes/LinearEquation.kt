package classes

class LinearEquation (private var matrix: Matrix, private val matrixRows: Int, private val matrixColumns: Int){


    public fun printSystemLinearEquation(){
        var matrixValue: Array<DoubleArray> = matrix.getMatrix()

        for(row in 1..matrixRows){
            for(column in 1..matrixColumns){
                print("${matrixValue[row-1][column-1]}x${column}")

                if(column == matrixColumns){
                    print("= ${matrixValue[row-1][column-1]}")
                }
                else{
                    print(" + ")
                }
            }
            println()

        }
    }

    private fun directStep(){
        for(k in 0..matrixRows-1){
            for(row in 0..matrixRows-1){
                val value: Double = matrix.getValue(row,0)
                for(column in 0..matrixColumns-1){
                    if(value != 0.0){
                        println("${matrix.getValue(row,column)}/${value}")
//                        var newValue: Double = matrix.getValue(row,column)/value;
                        val newValue: Double = (matrix.getValue(row,k) - matrix.getValue(k,column))/matrix.getValue(k,k)
                        matrix.setValue(row,column, newValue)

                    }

                }
            }
        }

    }
    public fun solverMethodGauss(){
        directStep()
        println("\nMatrix with one in column\n")
        matrix.printMatrix()

    }
}