package com.example.assessment_4662588

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddCar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_car)

        val actionAddCar : Button = findViewById(R.id.action_create)
        val addCarBrand : EditText = findViewById(R.id.car_brand)
        val addCarModel : EditText = findViewById(R.id.car_model)
        val addCarPrice : EditText = findViewById(R.id.car_price)

        actionAddCar.setOnClickListener{
            val validCarBrand: String = addCarBrand.text.toString()
            val validCarModel: String = addCarModel.text.toString()
            val validCarPrice: String = addCarPrice.text.toString()

            if (validCarBrand.trim().isEmpty()){
                Toast.makeText(applicationContext, "Enter Car Brand!", Toast.LENGTH_SHORT).show()
            }
            else if(validCarModel.trim().isEmpty()){
                Toast.makeText(applicationContext, "Enter Car Model!", Toast.LENGTH_SHORT).show()
            }
            else if(validCarPrice.trim().isEmpty()){
                Toast.makeText(applicationContext, "Enter Price!", Toast.LENGTH_SHORT).show()
            }
            else{
                val db = DBHelper(this, null)
                db.addCar(validCarBrand, validCarModel, validCarPrice)

                Toast.makeText(this, validCarBrand + " " + validCarModel + " added", Toast.LENGTH_LONG).show()

                addCarBrand.text.clear()
                addCarModel.text.clear()
                addCarPrice.text.clear()
            }
        }
    }
}