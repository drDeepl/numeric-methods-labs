
import classes.Matrix
import classes.LinearEquation
import java.io.File
import java.util.*
import kotlin.reflect.typeOf

fun main(args: Array<String>) {
    var isWork: Boolean = true
    while (isWork){
        println("\n1 - решение методом Гаусса\t2 - решение методом Джордана-Гаусса\t3 - решение методом LU разложения 4 - методом простых итераций\t0 - выход")
        val input = Scanner(System.`in`)
        val choice: Int = input.nextInt()
        if(choice == 0){
            break
        }
        val path: String = "src/resources/sle.txt"
        val lines: List<String> = File(path).readLines()
        var matrixCoef: Matrix = Matrix(lines.size,lines[0].split(" ").size)
        var linearEquation: LinearEquation = LinearEquation(matrixCoef, matrixCoef.getRows(), matrixCoef.getColumns())
        for(row in 0..lines.size-1){
            var line: List<String> = lines[row].split(" ")
            for(column in 0..< line.size){
                val value: Double = line[column].toDouble()
                matrixCoef.setValue(row,column, value)
            }
        }

        println("\nТекущая СЛАУ для решения:\n")
        linearEquation.printSystemLinearEquation()
        var x: DoubleArray = DoubleArray(matrixCoef.getRows())

        if(choice == 1){
            println("\nВыбрано решение методом Гаусса\n")
            x = linearEquation.solverMethodGauss()
        }
        else if(choice == 2){
            println("\nВыбрано решение методом Джордана-Гаусса\n")
            x = linearEquation.solverMethodsJordanGauss()
        }
        else if(choice == 3){
            println("\nВыбрано решение методом LU разложения\n")
            x = linearEquation.solverLUDecomposeV2()
        }
        else if (choice == 4){
            println("\nВыбрано решение методом простых итераций\n")
            println("Число итераций по умолчанию: 1000\nпогрешность 1/100")
            x = linearEquation.simpleIteration(1000, 1.0/1000)
        }

        for(i in 0..<x.size){
            print("x_${i+1} = ${x[i]}\t")
        }
        println()

    }

}

