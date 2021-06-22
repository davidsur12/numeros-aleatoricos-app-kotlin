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
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import app.numeros.numerosaleatoricos.R

class DialogoTextNumericoMax(btn :Button , btnCantidad: Button) : DialogFragment() , View.OnClickListener {

    lateinit var  numMax: EditText;
    lateinit var btnOk: Button;
    lateinit var btnCancel: Button;
    lateinit var btnP:Button;
    lateinit var btnC: Button;
    init {
        btnP = btn;
        btnC=btnCantidad;
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
       // return super.onCreateDialog(savedInstanceState)
        return activity?.let{
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            val vista= LayoutInflater.from(context).inflate(R.layout.dialogotextnummax, null);
            btnOk=vista.findViewById(R.id.btnOkNum1);
            btnCancel=vista.findViewById(R.id.btnCancelNum1);
            numMax=vista.findViewById(R.id.editNumMaximo);
            //eventos
            btnOk.setOnClickListener (this)
            btnCancel.setOnClickListener (this)
            numMax.addTextChangedListener(object : TextWatcher{
                var text: String = "";

                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                  if (p0.toString().length > 0 && p0.toString().toInt() > 999) {
                        numMax.setText(text);
                        numMax.setSelection(numMax.length());

                    } else {
                            text = p0.toString();
                    }
                }
            });

            builder.setView(vista);
            builder.create();



        }?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnOkNum1->{
                this.dismiss()
                if(validarInput(numMax.text.toString())){
                    ValoresConfig.numMaximo=numMax.text.toString().toInt();
                    btnP.setText("Numero maximo  ${ValoresConfig.numMaximo}");

                    if(ValoresConfig.cantidad > ValoresConfig.numMaximo-ValoresConfig.numMinimo){
                        ValoresConfig.cantidad=ValoresConfig.calcularCantidadMaxima();
                        btnC.setText("Numero maximo  ${ValoresConfig.cantidad}");
                    }

                }
            }
            R.id.btnCancelNum1->{
                getDialog()?.cancel();
            }
        }
    }
    fun validarInput(text: String): Boolean {
        if (text.length == 0) {
            return false
        } else {
            if (text.toInt() < ValoresConfig.numMinimo || text.toInt() > 999) {
                return false;
            } else {
                return true;
            }

        }
    }
}