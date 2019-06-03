package com.example.parcial1basketball.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "equipo_table",
    foreignKeys = arrayOf(
        ForeignKey(entity = Partido::class,parentColumns = arrayOf("partido_id"),childColumns = arrayOf("partido_fk"),onDelete = CASCADE)
    ))
data class Equipo(

    @ColumnInfo(name = "equipo_name") val equipo_name:String,
    @ColumnInfo(name = "equipo_puntaje") val equipo_puntaje:String,
    @ColumnInfo(name = "equipo_isWinner") val equipo_isWinner:Boolean,
    @ColumnInfo(name = "partido_fk") var partido_id:Long?

)

{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "equipo_id") var id: Long=0
}



