package app.numeros.numerosaleatoricos.Dialogo

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import app.numeros.numerosaleatoricos.R

class DialogoNumerosRepetidos(btn: Button) : DialogFragment(), View.OnClickListener {
    lateinit var btnOk: Button;
    lateinit var btnCancel: Button;
    lateinit var btnP: Button;
    lateinit var checksi: CheckBox;
    lateinit var checkno: CheckBox;

    init {
        btnP = btn;
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater;
            //var vista= inflater.inflate(R.layout.fragment_numeros_aleatoricos, false);
            val vista =
                LayoutInflater.from(context).inflate(R.layout.dialogo_numeros_repetidos, null)
            btnOk = vista.findViewById(R.id.btnOkNum3);
            btnCancel = vista.findViewById(R.id.btnCancelNum3);
            checksi = vista.findViewById(R.id.checksi);
            checkno = vista.findViewById(R.id.checkno);
            //eventos
            btnOk.setOnClickListener(this);
            btnCancel.setOnClickListener(this);
            //onCheckboxClicked(checksi);
            checksi.setOnCheckedChangeListener({ buttonView, isChecked ->
                //Toast.makeText(activity, "lol" + isChecked, Toast.LENGTH_SHORT).show();
                if(isChecked){
                    checkno.isChecked=false;
                    ValoresConfig.repetidos=true;


                }

            })


            checkno.setOnCheckedChangeListener({ buttonView, isChecked ->
                //Toast.makeText(activity, "lol" + isChecked, Toast.LENGTH_SHORT).show();
                if(isChecked){
                    checksi.isChecked=false;
                    ValoresConfig.repetidos=false;
                }
            })


            builder.setView(vista)

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnOkNum3 -> {
                this.dismiss()
                btnP.setText(ValoresConfig.textButton());


            }
            R.id.btnCancelNum3 -> {
                getDialog()?.cancel()
                btnP.setText(ValoresConfig.textButton());
            }


        }
    }

    fun controlChecBox() {
        if (checksi.isChecked) {
            checkno.setEnabled(false);
            ValoresConfig.repetidos=true;
        }
        if (checkno.isChecked) {
            checksi.setEnabled(false);
            ValoresConfig.repetidos=false;
        }

    }
    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.checksi -> {
                    if (checked) {
                        controlChecBox()
                        Toast.makeText(activity , "check" , Toast.LENGTH_SHORT).show();
                    } else {
                        // Remove the meat
                    }
                }
                R.id.checkno -> {
                    if (checked) {
                        controlChecBox()
                    } else {
                        // I'm lactose intolerant
                    }
                }

            }
        }
    }



}