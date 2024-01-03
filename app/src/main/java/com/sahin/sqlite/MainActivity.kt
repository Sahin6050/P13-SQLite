package com.sahin.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Verilerimizi kaydetmek için kullandığımız SQLite kodları try and catch bloğu altında yazılır.
        try {
            // Eğer bu isimde database yok ise oluştur.
            val myDatabase = this.openOrCreateDatabase("Musicians", MODE_PRIVATE,null)

            // Eğer tablo yok ise oluştur.
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY,name VARCHAR,age INT)")

            // Veri ekle.
            //myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES ('James',50)")
            //myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES ('Lars',60)")
            //myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES ('Kirk',55)")


            // UPDATE
            //myDatabase.execSQL("UPDATE musicians SET age = 43 WHERE name = 'James'")
            //myDatabase.execSQL("UPDATE musicians SET name = 'Cenk' WHERE name = 'Lars'")

            // DELETE
            myDatabase.execSQL("DELETE FROM musicians WHERE name = 'Cenk'")

            // İmleci kullanarak verileri çekme.filtrelemelerde burada yapılır.
            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE 'K%'",null)
            val cursor = myDatabase.rawQuery("SELECT * FROM musicians",null)

            // İndexlere göre imleçten veri çekme
            val idIx = cursor.getColumnIndex("id")
            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")

            // Verilerin sonuna kadar git.
            while (cursor.moveToNext()){
                println("id:"+cursor.getInt(idIx))
                println("Name:"+cursor.getString(nameIx))
                println("Age:"+cursor.getInt(ageIx))
            }
            // imleci kapat.
            cursor.close()

        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}