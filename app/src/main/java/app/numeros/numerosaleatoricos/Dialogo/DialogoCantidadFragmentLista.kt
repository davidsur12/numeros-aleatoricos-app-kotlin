package app.numeros.numerosaleatoricos.Dialogo

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import app.numeros.numerosaleatoricos.R

class DialogoCantidadFragmentLista(btn :Button) : DialogFragment() , View.OnClickListener {

    lateinit var cantidad: EditText;
    lateinit var btnOk: Button;
    lateinit var btnCancel: Button;
    lateinit var btnP: Button;
    var text:String="";

    init {
        btnP = btn;
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            val vista = LayoutInflater.from(context).inflate(R.layout.dialogo_cantidad_fragment_lista , null);
            btnOk = vista.findViewById(R.id.btnOkCantidadFragemnetLista);
            btnCancel = vista.findViewById(R.id.btnCancelCantidadFragemnetLista);
            cantidad = vista.findViewById(R.id.editCantidadFragmentLista);
            //eventos
            btnOk.setOnClickListener(this)
            btnCancel.setOnClickListener(this)
            cantidad.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0.toString().length > 0 && (p0.toString().toInt() > ValoresConfig.lista.size)){
                        //si la entrada es mayor al tamaño de la lista
                        cantidad.setText(text);
                        cantidad.setSelection(cantidad.length());



                    } else {


                        if(p0.toString().length > 0 && p0.toString().toInt() == 0){
                            text = "1";
                            cantidad.setText(text);
                            cantidad.setSelection(cantidad.length());

                        }else {
                            text = p0.toString();

                        }

                    }


                }
            });
            builder.setView(vista);
            builder.create();


        } ?: throw IllegalStateException("Activity cannot be null")

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnOkCantidadFragemnetLista->{
                this.dismiss()
                if(validarInput(cantidad.text.toString())){
                    ValoresConfig.cantidadFragmentLista = cantidad.text.toString().toInt();
                    btnP.setText("Cantidad   ${ValoresConfig.cantidadFragmentLista}");

                }
            }
            R.id.btnCancelCantidadFragemnetLista -> {
                getDialog()?.cancel();
            }
        }
    }
    fun validarInput(text : String):Boolean{
        if(text.length==0){
            return false
        }else{
            return true
        }
    }
}