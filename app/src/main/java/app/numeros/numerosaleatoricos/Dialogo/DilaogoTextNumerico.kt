package app.numeros.numerosaleatoricos.Dialogo

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import app.numeros.numerosaleatoricos.R
import java.nio.file.attribute.AclEntry
import app.numeros.numerosaleatoricos.R.color;

class DilaogoTextNumerico(btn: Button , btnCantidad: Button) : DialogFragment(), View.OnClickListener {
    lateinit var numMinimo: EditText;
    lateinit var btnOk: Button;
    lateinit var btnCancel: Button;
    lateinit var btnP: Button;
    lateinit var btnC: Button;

    init {
        btnP = btn;
        btnC=btnCantidad;
    }

    // lateinit   var context:Context
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater

            val inflater = requireActivity().layoutInflater;
            //var vista= inflater.inflate(R.layout.fragment_numeros_aleatoricos, false);
            val vista = LayoutInflater.from(context).inflate(R.layout.dialogo_texto_numerico, null)
            btnOk = vista.findViewById(R.id.btnOkNum);
            btnCancel = vista.findViewById(R.id.btnCancelNum);
            btnOk.setOnClickListener(this)
            btnCancel.setOnClickListener(this)
            numMinimo = vista.findViewById(R.id.editNumMinimo);
            numMinimo.addTextChangedListener(object : TextWatcher {
                var text: String = "";
                var estadoFin = false;
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0.toString().length > 0 && p0.toString().toInt() > 999) {
                        numMinimo.setText(text);
                        numMinimo.setSelection(numMinimo.length());



                    } else {

                        if(p0.toString().length > 0 &&  p0.toString().toInt() > ValoresConfig.numMaximo){
                            numMinimo.setText(text);
                            numMinimo.setSelection(numMinimo.length());
                        }else{
                            text = p0.toString();
                        }

                    }
                }
            });
            builder.setView(vista)

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
        // return super.onCreateDialog(savedInstanceState)
    }


    override fun onDestroy() {
        super.onDestroy()
        //Toast.makeText(context , "" + cantidad.text , Toast.LENGTH_SHORT).show();
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnOkNum -> {
                this.dismiss()
                if (validarInput(numMinimo.text.toString())) {
                    ValoresConfig.numMinimo = numMinimo.text.toString().toInt();
                    btnP.setText("Numero minimo  ${ValoresConfig.numMinimo}");

                    if(ValoresConfig.cantidad > ValoresConfig.numMaximo-ValoresConfig.numMinimo){
                        ValoresConfig.cantidad=ValoresConfig.calcularCantidadMaxima();
                        btnC.setText("Numero maximo  ${ValoresConfig.cantidad}");
                    }

                    //Toast.makeText(activity, "" + ValoresConfig.numMinimo, Toast.LENGTH_LONG).show()
                }

            }
            R.id.btnCancelNum -> {
                getDialog()?.cancel()
            }

        }
    }

    fun validarInput(text: String): Boolean {
        if (text.length == 0) {
            return false
        } else {
            if (text.toInt() > ValoresConfig.numMaximo || ValoresConfig.numMinimo > 999) {
                return false;
            } else {
                return true;
            }

        }
    }
}