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
                    if(column +1 != matrixColumns) {
                        print(" + ")
                    }
                    else{
                        print(" ")
                    }
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

        }
        return b
    }
    public fun printSolverSLinearE(answers: DoubleArray){
        var matrixValue: Array<DoubleArray> = matrix.getMatrix()

        for(row in 1..matrixRows){
            for(column in 1..matrixColumns-1){



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

        for(i in matrixRows-2 downTo 0){
            var sum: Double = matrix.getValue(i, matrixRows-1)
            for(j in i+1 downTo 0){
               sum = sum - matrix.getValue(i,j)*x[j]
            }
            x[i] = sum/matrix.getValue(i,i)

        }
    }
    public fun solverMethodGauss(): DoubleArray{
        val size: Int = matrix.getRows()
        val x: DoubleArray = DoubleArray(size)
        for(i in 0..<size){
            var tmp: Double = matrix.getValue(i,i)
            for(j in size downTo i){
                matrix.setValue(i,j, matrix.getValue(i,j)/tmp)
            }
            for(j in i+1..<size){
                tmp = matrix.getValue(j,i)
                for(k in size downTo i){
                    var a_jk: Double = matrix.getValue(j,k) - (matrix.getValue(i,k) * tmp)
                    matrix.setValue(j,k, a_jk)
                }
            }
        }
        x[size-1] = matrix.getValue(size-1,size)
        for(i in size-2 downTo 0){
            x[i] = matrix.getValue(i,size)
            for(j in i+1..<size){
                x[i] -= matrix.getValue(i,j) * x[j]
            }
        }
        return x
    }

    fun solverMethodsJordanGauss(): DoubleArray {
        var a: Array<DoubleArray> = matrix.getMatrix()
        val size: Int = matrix.getRows()
        var x: DoubleArray = DoubleArray(size)
        for(i in 0..<size){
            for(j in 0..<size){
                if(i != j){
                    var ratio: Double = a[j][i]/a[i][i]
                    for(k in 0..size){
                        a[j][k] -= ratio * a[i][k]
                    }
                }
            }
        }

        for(i in 0..<size){
            x[i] = a[i][size]/a[i][i]
        }
        return x
    }

    fun luSolve(l:Array<DoubleArray>, u:Array<DoubleArray>, b:DoubleArray): DoubleArray{
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

        return x
    }
    fun solverLUDecomposeV2(): DoubleArray{
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
        return luSolve(l,u,b)
    }

}