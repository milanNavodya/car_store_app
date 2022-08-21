package com.example.assessment_4662588

import android.annotation.SuppressLint
import android.database.CursorIndexOutOfBoundsException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CheckCar : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_car)

        val actionCheckCar : Button = findViewById(R.id.action_check)
        val checkCarBrand : EditText = findViewById(R.id.check_car_brand)
        val checkCarModel : EditText = findViewById(R.id.check_car_model)
        val checkCatPrice : EditText = findViewById(R.id.car_price)

        val dbChecker = DBHelper(baseContext, null)

        actionCheckCar.setOnClickListener {
            val checkCarBrandString : String = checkCarBrand.text.toString()
            val checkCarModelString : String = checkCarModel.text.toString()

            if(checkCarBrandString.trim().isEmpty()){
                Toast.makeText(applicationContext, "Enter Car Brand!", Toast.LENGTH_SHORT).show()
            }
            else if(checkCarModelString.trim().isEmpty()){
                Toast.makeText(applicationContext, "Enter Car Model!", Toast.LENGTH_SHORT).show()
            }
            else{
                val checkCar = dbChecker.getCarPRice(checkCarBrandString, checkCarModelString)
                try{
                    checkCar!!.moveToFirst()
                    checkCatPrice.append("Rs." + checkCar.getString(checkCar.getColumnIndex(DBHelper.PRICE)))

                    while (checkCar.moveToNext())
                    {
                        checkCatPrice.append("Rs." + checkCar.getString(checkCar.getColumnIndex(DBHelper.PRICE)))
                    }

                    checkCar.close()
                }

                catch (e: CursorIndexOutOfBoundsException)
                {
                    Toast.makeText(this, "Entry not found!", Toast.LENGTH_LONG).show()
                    checkCarBrand.text.clear()
                    checkCarModel.text.clear()
                    checkCatPrice.setText("")
                }
            }
        }
    }
}