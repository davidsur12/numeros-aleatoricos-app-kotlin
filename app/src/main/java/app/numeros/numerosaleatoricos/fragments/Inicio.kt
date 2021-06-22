package app.numeros.numerosaleatoricos.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import app.numeros.numerosaleatoricos.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Inicio.newInstance] factory method to
 * create an instance of this fragment.
 */
class Inicio : Fragment() , View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var btnNUm:ImageButton;
    lateinit var btnLista:ImageButton;
    lateinit var btnRueda:ImageButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    interface OnArticleSelectedListener {
        fun onArticleSelected(id: Int)
    }
    var listener: OnArticleSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnArticleSelectedListener
        if (listener == null) {
            throw ClassCastException("$context must implement OnArticleSelectedListener")
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista:View=inflater.inflate(R.layout.fragment_inicio, container, false);
        btnNUm = vista.findViewById(R.id.btnNum);
        btnLista = vista.findViewById(R.id.btnLista);
        btnRueda = vista.findViewById(R.id.btnRueda);


        btnNUm.setOnClickListener(this);
        btnRueda.setOnClickListener(this);
        btnLista.setOnClickListener(this);


        return  vista;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Inicio.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Inicio().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){

            (R.id.btnNum) -> { listener?.onArticleSelected(1)

                //Toast.makeText( activity ,  "num" , Toast.LENGTH_SHORT).show();
                }
            (R.id.btnLista) -> Toast.makeText( activity ,  "lista" , Toast.LENGTH_SHORT).show();
            (R.id.btnRueda) -> Toast.makeText( activity ,  "rueda" , Toast.LENGTH_SHORT).show();
        }

    }
}