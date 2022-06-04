package ir.mjahandar.baseconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import kotlin.math.pow


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Define Widgets
        val edInput:EditText = findViewById(R.id.txt_input)
        val rb2t10: RadioButton = findViewById(R.id.rb_2to10)
        val rb10t2: RadioButton = findViewById(R.id.rb_10to2)
        val btnConvert: Button = findViewById(R.id.btn_convert)
        val btnReset: Button = findViewById(R.id.btn_reset)
        val txtOutput:TextView = findViewById(R.id.txt_output)


        fun convertFrom2to10(num: Long): Int {
            var num = num
            var decimalNumber = 0
            var i = 0
            var remainder: Long

            while (num.toInt() != 0) {
                remainder = num % 10
                num /= 10
                decimalNumber += (remainder * 2.0.pow(i.toDouble())).toInt()
                ++i
            }
            return decimalNumber
        }


        fun convertFrom10to2(num:Int){
            var number:Int = num
            var reminder:Int
            var result: String = ""

            while (number !=0){
                reminder = number % 2
                result =  reminder.toString() + result
                number /= 2
            }
            txtOutput.text = result
        }

        btnConvert.setOnClickListener {

            if (edInput.text.isBlank()){

                MotionToast.createColorToast(this, "Empty Field!","Please Fill the Input",
                    MotionToastStyle.WARNING,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(this,R.font.helvetica_regular))
            }
            else{

                if (rb2t10.isChecked){
                    // Convert 2 to 10
                    val inputNumber: Long = edInput.text.toString().toLong()
                    val decimal = convertFrom2to10(inputNumber)
                    txtOutput.text = decimal.toString()
                }
                else{
                    // Convert 10 to 2
                    var inputNumber: Int = edInput.text.toString().toInt()
                    convertFrom10to2(inputNumber)
                }
            }
        } // end of setOnClickListener

        btnReset.setOnClickListener {
            edInput.text.clear()
            txtOutput.text = ""
            edInput.requestFocus()
        }
    } // end of onCreate
} // end of class