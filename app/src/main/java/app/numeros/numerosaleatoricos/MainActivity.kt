package app.numeros.numerosaleatoricos

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.HeaderViewListAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import app.numeros.numerosaleatoricos.Dialogo.ValoresConfig
import app.numeros.numerosaleatoricos.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() , Inicio.OnArticleSelectedListener  , Lista.Notificacion {

    lateinit var btnNav:BottomNavigationView;
    var fragmentInicio : Inicio? = null ;
    lateinit var fragmentNumeros: NumerosAleatoricos;
    lateinit var fragmentLista: Lista;
    var menuFragment:Int=0;
    var estadoMostrarMenu:Boolean=false;
    lateinit  var fragmentEditarLista:EditarListaFragment;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        cambiarFragment(0)//cambio el fragment en el numeros aleatoricos como fragment inicial
        btnNav=findViewById(R.id.btnNav);
        btnNav.setOnNavigationItemSelectedListener {
                menuItem ->when(menuItem.itemId){
           R.id.Numeros -> {
               cambiarFragment(0)
              // Toast.makeText(applicationContext , "toolbar opcion 1" , Toast.LENGTH_SHORT).show();
               menuFragment=0;
               true}
            R.id.Lista -> {
               // Toast.makeText(applicationContext , "toolbar opcion 2" , Toast.LENGTH_SHORT).show();
                cambiarFragment(1)
                true}
            R.id.Rueda-> {
                Toast.makeText(applicationContext , "toolbar opcion 3" , Toast.LENGTH_SHORT).show();
                //cambiarFragment(0)
                true}
            else->  false;

        } }


    }
    fun cambiarFragment(id : Int){
        val transaction = supportFragmentManager.beginTransaction()
        when(id){
            0 -> {
                fragmentNumeros = NumerosAleatoricos();
                transaction.replace(R.id.viewf, fragmentNumeros!!)
                transaction.addToBackStack(null)
                transaction.commit()
                //cambio el menu del toolbar
            }
            1 -> {
                fragmentLista = Lista();
                transaction.replace(R.id.viewf, fragmentLista!!)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            4 -> {
                fragmentEditarLista= EditarListaFragment();
                transaction.replace(R.id.viewf, fragmentEditarLista!!)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }




    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {//opciones de toolbar
        //menuInflater.inflate(R.menu.confignum , menu)
        return true;
        //return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {//eventos del toolbar
        return when(item.itemId){
            R.id.menu -> {
                if(!estadoMostrarMenu){
                    estadoMostrarMenu=true;
                }else{
                    estadoMostrarMenu=false
                }
              // callback.onArticleSelected(estadoMostrarMenu);
                fragmentNumeros.cambiar(estadoMostrarMenu)

                true;
                 }
            else ->  super.onOptionsItemSelected(item)
        }

    }

    override fun onArticleSelected(id: Int) {
        cambiarFragment(1)
       // Toast.makeText(this , "cambio" ,Toast.LENGTH_SHORT ).show();
    }

    override fun onAttachFragment(fragment: android.app.Fragment?) {
        super.onAttachFragment(fragment)

    }

    override fun cambiarFragmentInterface(id: Int) {

        cambiarFragment(4);
    }

    //inerfz para comunicar la activity con el fragment

   //var callback :OnHeadLineSelectedListener = OnHeadLineSelectedListener();


}