package com.example.sql_database_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    var i : Int = 0
    var arr : ArrayList<Product> = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dbUtil = DBUtil(this)
        dbUtil.addProducts(101,"Prodcut101",1001)
        dbUtil.addProducts(102,"Prodcut102",1002)
        dbUtil.addProducts(103,"Prodcut103",1003)

        for (i in 104..110){
            dbUtil.addProducts(i,"Product$i",i*10)
        }

        arr = dbUtil.retriveProducts()
        for (eachProduct in arr){
                Log.e("tag","${eachProduct.id} -- ${eachProduct.title} -- ${eachProduct.price}")
        }

        dbUtil.deleteProducts(101)

        for (eachProduct in dbUtil.retriveProducts()){
            Log.e("tag","${eachProduct.id} -- ${eachProduct.title} -- ${eachProduct.price}")
        }

        dbUtil.updateProducts(104,"product180",10080)
        for (eachProduct in dbUtil.retriveProducts()){
            Log.e("tag","${eachProduct.id} -- ${eachProduct.title} -- ${eachProduct.price}")
        }
    }
}