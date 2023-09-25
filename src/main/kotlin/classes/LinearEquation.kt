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

    fun getBPart(): DoubleArray{
        var size: Int = matrix.getRows()
        var b: DoubleArray = DoubleArray(size)
        for(i in 0..<size){
            b[i] = matrix.getValue(i, size)
            println(matrix.getValue(i, size))
        }
        return b
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
//        directStep()
//        println("\nMatrix with one in column\n")
//        reverseStep()
//        matrix.printMatrix()
        var a: Array<DoubleArray> = matrix.getAPart()
        var b: DoubleArray = matrix.getBPart()
        val size: Int = a.size
        var x: DoubleArray = DoubleArray(size)
        for(k in 0..<size-1){
            for(i in k+1..<size){
                var pivot: Double = a[i][k]/a[k][k]
                for(j in k+1..<size){
                    a[i][j] -= pivot * a[k][j]
                }
                b[i] -+ pivot * b[k]
            }
        }


        for(i in size-1 downTo 0){
            var sum: Double = b[i]
            for(j in i+1..<size){
                sum -= a[i][j] * x[j]
            }
            x[i] = sum/a[i][i]
            println("x_${i+1} = ${x[i]}")
        }

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

    fun luSolve(l:Array<DoubleArray>, u:Array<DoubleArray>, b:DoubleArray){
        val size: Int = matrix.getRows()
        var y: DoubleArray= DoubleArray(size)
        var x: DoubleArray = DoubleArray(size)

        // Ly = b
        for(i in 0..<size){
            var sum: Double = 0.0
            for(j in 0..<i){
                sum += l[i][j] * y[j]
            }
            y[i] = (b[i] - sum)/l[i][i]
        }
        // Ux = y

        for(i in size-1 downTo 0){
            var sum: Double = 0.0
            for(j in i+1..<size){
                sum += u[i][j] * x[j]
            }
            x[i] = (y[i] - sum)/u[i][i]
        }

        for(i in 0..<x.size){
            println("x_${i+1} = ${x[i]}")
        }
    }
    fun solverLUDecomposeV2(){
        println("\nSolve by LU Decompose\n")
        var l: Array<DoubleArray> = Array(matrix.getRows()){DoubleArray(matrix.getRows())}
        var u: Array<DoubleArray> = Array(matrix.getRows()){DoubleArray(matrix.getRows())}
        val size = matrix.getRows()
        for(i in 0..<size){
            for(k in i..<size){
                var sum: Double = 0.0
                for(j in 0..<i){
                    sum+= (l[i][j] * u[j][k])
                }
                u[i][k] = matrix.getValue(i,k) - sum
            }

            for(k in i..<size){
                if(i==k){
                    l[i][k] = 1.0
                }
                else{
                    var sum: Double = 0.0
                    for(j in 0..<i){
                        sum += (l[k][j] * u[j][i])
                    }
                    l[k][i] = (matrix.getValue(k,i) - sum)/u[i][i]
                }
            }
        }

        var b: DoubleArray = getBPart()
        luSolve(l,u,b)
    }

}