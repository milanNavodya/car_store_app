package com.example.assessment_4662588

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addCar : Button = findViewById(R.id.add_car)
        val checkCar : Button = findViewById(R.id.check_car)

        addCar.setOnClickListener{
            val addCarIntent = Intent(this, AddCar::class.java)
            startActivity(addCarIntent)
        }

        checkCar.setOnClickListener{
            val checkCarIntent = Intent(this, CheckCar::class.java)
            startActivity(checkCarIntent)
        }
    }
}