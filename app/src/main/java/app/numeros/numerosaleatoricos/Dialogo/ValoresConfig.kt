package app.numeros.numerosaleatoricos.Dialogo

import android.app.ActionBar
import android.widget.Toolbar

object ValoresConfig {
    var numMinimo: Int = 0;//valores por defecto
    var numMaximo: Int = 30;//valores por defecto
    var cantidad: Int = 30;//valores por defecto
    var repetidos: Boolean = false;//valores por
    var cantidadFragmentLista: Int = 0;
    var idtoolbar: Int = 0;
    lateinit var lista: List<String>;//lista para el fragment lista

    lateinit var toolbar: ActionBar;
    fun valores() {

    }

    fun textButton(): String {
        var result: String = "";
        if (ValoresConfig.repetidos) {
            result = "Repetidos   SI";
        } else {
            result = "Repetidos   NO";
        }
        return result;

    }

    fun valoresAleatoricosRandom(): IntArray {

        val array = IntArray(cantidad);
        // val n=(numMinimo..numMaximo).random();
        for (i in 0..cantidad - 1) {
            array[i] = (numMinimo..numMaximo).random();

        }
        return array;
    }

    fun valoresAleatoricosNoRepetidos(): IntArray {

        val array = IntArray(cantidad);
        //array[0]=2;
        var num: Int = 0;
        // val n=(numMinimo..numMaximo).random();
        for (i in 0..cantidad - 1) {
            while (true) {
                num = (numMinimo..numMaximo).random();
                if (num !in array) {
                    array[i] = num;
                    break;
                }
            }


        }
        return array;

    }

    fun calcularCantidadMaxima(): Int {

        var resultado: Int = numMaximo - numMinimo;
        if (resultado == 0) {
            resultado = 1;
        } else {
            resultado + 1;
        }
        return resultado;
    }




}