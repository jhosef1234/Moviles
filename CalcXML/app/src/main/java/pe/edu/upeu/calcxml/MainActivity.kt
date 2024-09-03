package pe.edu.upeu.calcxml

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.PI


class MainActivity : AppCompatActivity() {
    private lateinit var txtResultado:EditText
    private var valAnt=0.0
    private var valAct=0.0
    private var operador=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtResultado=findViewById(R.id.txtResult)
        botones();

    }

    fun botones(){
        var buttons= arrayOf(R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnMult, R.id.btn4,R.id.btn1,R.id.btn2,R.id.btn3, R.id.btn5, R.id.btn6, R.id.btnSum, R.id.btnIgual, R.id.btnPot, R.id.btnRaiz,R.id.btnFrac,R.id.btnPi)
        for (button in buttons){
            var btn=findViewById<Button>(button)
            btn.setOnClickListener { onClikListener(btn) }
        }
    }

    fun onClikListener(view:View){
        var botonX=findViewById<Button>(view.id)
        when(view.id){
            R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9->{
                appentContent(botonX.text.toString()) }
            R.id.btnMult, R.id.btnSum, R.id.btnPot, R.id.btnRaiz, R.id.btnPi, R.id.btnFrac->{
                setOperador(botonX.text.toString())
            }

            R.id.btnFrac -> {
                val valor = txtResultado.text.toString().toDouble()
                val resultado = 1 / valor
                txtResultado.setText(resultado.toString())
            }
            R.id.btnPi -> {
                txtResultado.setText(PI.toString())
            }
            R.id.btnIgual->{
                operacion()
            }
        }
    }

    fun appentContent(valor:String){
        txtResultado.append(valor)
    }

    fun setOperador(oper:String){
        operador=oper
        valAnt=txtResultado.text.toString().toDouble()
        txtResultado.text.clear()
    }

    fun operacion(){
        valAct=txtResultado.text.toString().toDouble()
        var resultx=when(operador){
            "*"->valAnt*valAct
            "+"->valAnt+valAct
            "^" -> Math.pow(valAnt, valAct)
            "√" -> Math.pow(valAct, 1 / valAnt)
            else->valAct
        }
        txtResultado.setText(resultx.toString())
        operador=""
        }
}