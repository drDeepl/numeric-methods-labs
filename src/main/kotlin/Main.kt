
import classes.Matrix
import classes.LinearEquation
fun main(args: Array<String>) {
    var matrixCoef: Matrix = Matrix(3,4)
    var linearEquation: LinearEquation = LinearEquation(matrixCoef, matrixCoef.getRows(), matrixCoef.getColumns())
//    matrixCoef.genRandomMatrix()
    println("\nMatrix Coefficient of linear equation\n")
    matrixCoef.printMatrix()
//    println("\nSystem of linear equation with matrix coeff\n")
//    linearEquation.printSystemLinearEquation()
    println("\nSolve by method Gauss\n")
    linearEquation.solverMethodGauss()

//    var xByJG: DoubleArray = linearEquation.solverMethodsJordanGauss()
//    for(i in 0..<xByJG.size){
//        println("x_${i+1} = ${xByJG[i]}")
//    }
//    linearEquation.solverLUDecomposeV2()

}

