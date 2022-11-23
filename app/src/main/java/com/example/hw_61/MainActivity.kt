package com.example.hw_61

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hw_61.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    companion object{
        private  const val KEY_SEND = "key"
        private const val KEY_BACK = "key_result"
    }

    private lateinit var launchResult: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClick()
        getResult()
    }

    private fun onClick() {
        binding.btnSend.setOnClickListener {
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

    private fun intent(text: String) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(KEY_SEND, text)
        launchResult.launch(intent)
    }

    fun getResult() {
        launchResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val intent: Intent? = it.data
                val title = intent?.getStringExtra(KEY_BACK)
                binding.edText.setText(title)
            }
        }
    }


}