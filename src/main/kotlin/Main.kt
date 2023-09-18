import classes.Matrix
import classes.LinearEquation
fun main(args: Array<String>) {
    var matrixCoef: Matrix = Matrix(3,4)
    var linearEquation: LinearEquation = LinearEquation(matrixCoef, matrixCoef.getRows(), matrixCoef.getColumns())
//    matrixCoef.genRandomMatrix()
    println("\nMatrix Coefficient of linear equation\n")
    matrixCoef.printMatrix()
    println("\nSystem of linear equation with matrix coeff\n")
    linearEquation.printSystemLinearEquation()
//    linearEquation.solverMethodGauss() 2 -1 1
    println()

    var xByJG: DoubleArray = linearEquation.solverMethodsJordanGauss()


}

