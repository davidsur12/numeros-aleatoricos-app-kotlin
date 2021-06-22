package app.numeros.numerosaleatoricos.fragments

import android.app.ActionBar
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toolbar
import app.numeros.numerosaleatoricos.Dialogo.DialogoCantidadFragmentLista
import app.numeros.numerosaleatoricos.Dialogo.DilaogoTextNumerico
import app.numeros.numerosaleatoricos.Dialogo.ValoresConfig.numMinimo
import app.numeros.numerosaleatoricos.R
import app.numeros.numerosaleatoricos.Dialogo.ValoresConfig

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Lista.newInstance] factory method to
 * create an instance of this fragment.
 */
class Lista() : Fragment() , View.OnClickListener  {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var btnCantidad: Button;
    private lateinit var btnEditarLista: Button;
    var estadoMostrarMenu: Boolean = false;
    lateinit var linearLayout: LinearLayout;
    lateinit var  notificacion: Notificacion;
    interface Notificacion{
        fun cambiarFragmentInterface(id : Int);
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Notificacion){
            notificacion=context;
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view = inflater.inflate(R.layout.fragment_lista, container, false)

        //enlazo
        btnCantidad = view.findViewById(R.id.btnCantidadLista);
        btnEditarLista = view.findViewById(R.id.btnEditarLista);
        linearLayout = view.findViewById(R.id.layoutConfigLista);
        //cambio el texto
        btnCantidad.setText("Numero minimo      ${ValoresConfig.cantidadFragmentLista} ");

        //eventos
        btnCantidad.setOnClickListener(this);
        btnEditarLista.setOnClickListener(this)
        cambiar(estadoMostrarMenu, linearLayout);
        //lleno la lista
        ValoresConfig.lista= listOf("1" , "2" , "3");

        return view;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Lista.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Lista().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menulista, menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu2 -> {
                if (!estadoMostrarMenu) {
                    estadoMostrarMenu = true;
                } else {
                    estadoMostrarMenu = false
                }
                cambiar(estadoMostrarMenu, linearLayout);
                //layout.visibility = View.VISIBLE;
                // callback.onArticleSelected(estadoMostrarMenu);
                // fragmentNumeros.cambiar(estadoMostrarMenu)

                true;
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun cambiar(viewMenu: Boolean, layout: LinearLayout) {
        // cambiarValores();
        if (viewMenu) {
            layout.visibility = View.VISIBLE;
        } else {
            layout.visibility = View.GONE;
        }
    }

    override fun onClick(p0: View?) {
       when(p0?.id){
           R.id.btnCantidadLista->{
               var dialog = DialogoCantidadFragmentLista(btnCantidad);
               dialog.show(requireActivity().supportFragmentManager, "view");
               Toast.makeText(this.context , "tamaño lista ${ValoresConfig.lista.size}" , Toast.LENGTH_SHORT).show();

           }
           R.id.btnEditarLista ->{
           notificacion.cambiarFragmentInterface(1);
           }

       }
    }
}