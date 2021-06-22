package app.numeros.numerosaleatoricos.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.core.view.marginRight
import androidx.core.view.marginStart
import androidx.core.view.setPadding
import app.numeros.numerosaleatoricos.Dialogo.*
import app.numeros.numerosaleatoricos.Dialogo.ValoresConfig
import app.numeros.numerosaleatoricos.MainActivity
import app.numeros.numerosaleatoricos.R
import app.numeros.numerosaleatoricos.R.color
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NumerosAleatoricos.newInstance] factory method to
 * create an instance of this fragment.
 */
class NumerosAleatoricos : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var btngenerar: ImageButton;

    //Layout confugurable
    lateinit var layout: LinearLayout;

    //Botones
    lateinit var btnNumMaximo: Button;
    lateinit var btnNumMInimo: Button;
    lateinit var btnCantidad: Button;
    lateinit var btnRepetido: Button;
    lateinit var txt: TextView;

    //valores de configuracion
    private var numMinimo: Int = 0;//valores por defecto
    private var numMaximo: Int = 10;//valores por defecto
    private var cantidad: Int = 9;//valores por defecto
    private var repetidos: Boolean = false;//valores por defecto
    lateinit var linearl: TableLayout;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_numeros_aleatoricos, container, false)
        //inflate
        layout = vista.findViewById(R.id.fconfig)
        btnNumMaximo = vista.findViewById(R.id.btnNumMax);
        btnNumMInimo = vista.findViewById(R.id.btnNumMin);
        btnCantidad = vista.findViewById(R.id.btnCant);
        btnRepetido = vista.findViewById(R.id.btnRepetido);
        btngenerar = vista.findViewById(R.id.estado);
        //cambio los valores de la configuracion
        cambiarValores();
        //eventos
        btnNumMInimo.setOnClickListener(this);
        btnNumMaximo.setOnClickListener(this);
        btnCantidad.setOnClickListener(this);
        btnRepetido.setOnClickListener(this);
        btngenerar.setOnClickListener(this);

        //prueba num aleatoricos
      //  txt = vista.findViewById<TextView>(R.id.numAleatoricos);

        //crear tableLayout
        linearl = vista.findViewById(R.id.layout);
        //generarNumeros(linearl);

        return vista;
    }

    fun generarNumeros(linearl: TableLayout) {

        var cont: Int = 0
        var met: DisplayMetrics = DisplayMetrics();
        activity?.windowManager?.defaultDisplay?.getMetrics(met);
        var widthp = met.widthPixels;
        //var r= arrayOf<TableRow>();
        var row: MutableList<TableRow> = mutableListOf<TableRow>();
        //var textview:MutableList<TextView> = mutableListOf<TextView>();
        var c: Color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Color.valueOf(1.0f, 1.0f, 1.0f)

        } else {
            TODO("VERSION.SDK_INT < O")
        };
        var fin: Boolean = false;
        var i = 0;
        var arraynum: IntArray;
        if (ValoresConfig.repetidos) {
            arraynum = ValoresConfig.valoresAleatoricosRandom();
        } else {
            arraynum = ValoresConfig.valoresAleatoricosNoRepetidos();
        }
        while (!fin) {//filas
            row.add(TableRow(this.context));
            var textview: MutableList<TextView> = mutableListOf<TextView>();
            var linearlayouth: LinearLayout = LinearLayout(this.context);
            for (j in 0..4) {

                textview.add(TextView(this.context));//creo un nuevo texview
                textview[j].textSize = 25f;
                textview[j].setPadding(30);
                textview[j].setHintTextColor(c.toArgb());
                textview[j].setTextColor(c.toArgb());
                textview[j].text = arraynum[cont].toString();
                textview[j].width = (widthp / 5);
                textview[j].textAlignment = View.TEXT_ALIGNMENT_CENTER;
                textview[j].setBackgroundResource(R.drawable.circle);

                row[i].addView(textview[j]);
                cont++;

                if (cont == ValoresConfig.cantidad) {
                    fin = true;
                    break;
                }


            }

            linearl.addView(row[i]);
            i++;


        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NumerosAleatoricos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NumerosAleatoricos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(p0: View?) {
        when (p0?.id) {

            (R.id.estado) -> {
                // este boton genera  los numeros al precionarlo

                linearl.removeAllViews();//limpio el tableLayout
                generarNumeros(linearl);
            }
            R.id.btnNumMin -> {
                var dialog = DilaogoTextNumerico(btnNumMInimo, btnCantidad);
                dialog.show(requireActivity().supportFragmentManager, "view");


                // d.onDestroy();

            }
            R.id.btnNumMax -> {
                var dialog = DialogoTextNumericoMax(btnNumMaximo, btnCantidad);
                dialog.show(requireActivity().supportFragmentManager, "view");
                // d.onDestroy();

            }
            R.id.btnCant -> {
                var dialog = DialogoTextCantidad(btnCantidad);
                dialog.show(requireActivity().supportFragmentManager, "view");
                // d.onDestroy();

            }
            R.id.btnRepetido -> {
                var dialog = DialogoNumerosRepetidos(btnRepetido);
                dialog.show(requireActivity().supportFragmentManager, "view");
                // d.onDestroy();

            }
            (R.id.btnLista) -> Toast.makeText(activity, "lista", Toast.LENGTH_SHORT).show();
            (R.id.btnRueda) -> Toast.makeText(activity, "rueda", Toast.LENGTH_SHORT).show();
        }
    }


    fun cambiar(viewMenu: Boolean) {
        cambiarValores();
        if (viewMenu) {
            layout.visibility = View.VISIBLE;
        } else {
            layout.visibility = View.GONE;
        }
    }

    fun cambiarValores() {

        btnNumMInimo.setText("Numero minimo      ${ValoresConfig.numMinimo} ");
        btnNumMaximo.setText("Numero maximo      ${ValoresConfig.numMaximo}");
        btnCantidad.setText("Cantidad      ${ValoresConfig.cantidad}");

        btnRepetido.setText(ValoresConfig.textButton());

    }

    fun mostrarDialogo() {
        val context = activity?.applicationContext;
        val builder = AlertDialog.Builder(context);
        builder.setTitle("lista")

        val view = layoutInflater.inflate(R.layout.dialogo_texto_numerico, null);
        // val editText=view.findViewById(R.id.editNumMinimo);

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.confignum , menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}