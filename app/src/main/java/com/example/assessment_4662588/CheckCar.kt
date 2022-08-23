package com.example.assessment_4662588

import android.annotation.SuppressLint
import android.database.CursorIndexOutOfBoundsException
import android.database.SQLException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Exception

class CheckCar : AppCompatActivity() {
    @SuppressLint("Range")  // ignore warning message for related element
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_car)

        val actionCheckCar : Button = findViewById(R.id.action_check)
        val checkCarBrand : EditText = findViewById(R.id.check_car_brand)
        val checkCarModel : EditText = findViewById(R.id.check_car_model)
        val checkCarPrice : EditText = findViewById(R.id.car_price)

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
                try{
                    val checkCar = dbChecker.getCarPRice(checkCarBrandString, checkCarModelString)
                    checkCar!!.moveToFirst()
                    checkCarPrice.append("Rs." + checkCar.getString(checkCar.getColumnIndex(DBHelper.PRICE)) + ", ")

                    while (checkCar.moveToNext())
                    {
                        checkCarPrice.append("Rs." + checkCar.getString(checkCar.getColumnIndex(DBHelper.PRICE)) + ", ")
                    }

                    checkCar.close()
                }

                catch (sqle: SQLException){
                    sqle.printStackTrace()
                }
                catch (cioobe: CursorIndexOutOfBoundsException)
                {
                    Toast.makeText(this, "Entry not found!", Toast.LENGTH_LONG).show()
                    checkCarBrand.text.clear()
                    checkCarModel.text.clear()
                    checkCarPrice.setText("")
                    cioobe.printStackTrace()
                }
                catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}