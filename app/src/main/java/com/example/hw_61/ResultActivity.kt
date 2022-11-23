package com.example.hw_61

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hw_61.databinding.ActivitySecondBinding

class ResultActivity : AppCompatActivity() {

    private lateinit  var binding: ActivitySecondBinding
    companion object{
        private  const val KEY_SEND = "key"
        private const val KEY_BACK = "key_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getText()
        backResult()
    }

    fun backResult(){
        binding.btnBackSend.setOnClickListener {
            val text = binding.edText.text.toString()
            if (text.isNotEmpty()) {
                intent(text)
            } else {
                Toast.makeText(
                    this,
                    "поле не заполнино",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun getText(){
        val extras: Bundle? = intent.extras
        val title = extras?.getString(KEY_SEND)
        binding.edText.setText(title)
    }

    fun intent(text:String){
        val intent = Intent()
        intent.putExtra(KEY_BACK, text)
        setResult(RESULT_OK, intent)
        finish()
    }
}