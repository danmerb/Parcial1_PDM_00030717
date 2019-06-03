package com.example.parcial1basketball.room.entities
import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "partido_table")
data class Partido (

    @ColumnInfo(name = "partido_hora") val partido_hora:String,
    @ColumnInfo(name = "partido_fecha") val partido_fecha:String
): Parcelable {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "partido_id") var id_partido: Long=0

    constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readString()) {
        id_partido = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(partido_hora)
        parcel.writeString(partido_fecha)
        parcel.writeLong(id_partido)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Partido> {
        override fun createFromParcel(parcel: Parcel): Partido {
            return Partido(parcel)
        }

        override fun newArray(size: Int): Array<Partido?> {
            return arrayOfNulls(size)
        }
    }
}






