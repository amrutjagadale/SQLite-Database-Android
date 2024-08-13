package com.example.sql_database_demo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class DBUtil(private val context: Context) {

    private  val db : SQLiteDatabase =
        ProductsDBHelper(context,"db products",null,1).writableDatabase

    fun addProducts(id : Int, name : String , price : Int):Boolean{
        val values = ContentValues()
        values.put("id",id)
        values.put("title",name)
        values.put("price",price)

        val rowNum  = db.insert("products",null,values)
        Log.e("tag","${rowNum}")
        return rowNum.toInt() != -1
    }
    fun deleteProducts(id : Int):Boolean{
        var count = db.delete("product","id=?", arrayOf(id.toString()))
        return count != 0
    }

    fun updateProducts(id:Int,name:String,price: Int):Boolean{
        val values = ContentValues()
        values.put("title",name)
        values.put("price",price)
        var count = db.update("products",values,"id=?", arrayOf(id.toString()))
        return count != 0
    }
    fun retriveProducts():ArrayList<Product>{
        var c = db.query("products",null,null,null,null,null,"id desc")
        val products1 = ArrayList<Product>()
        while (c.moveToNext()){
            val id = c.getInt(0);
            val title = c.getString(1)
            val price = c.getInt(2)
            val newProduct = Product(id, title, price)
            products1.add(newProduct)
        }
        c.close()
        return products1
    }
}