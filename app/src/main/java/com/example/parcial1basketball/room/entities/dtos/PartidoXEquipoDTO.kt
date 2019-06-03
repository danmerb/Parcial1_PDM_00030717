package com.example.parcial1basketball.room.entities.dtos

import android.os.Parcel
import android.os.Parcelable

class PartidoXEquipoDTO (
    var eq1:String,
    var eq2:String,
    var eq1Score:String,
    var eq2Score:String,
    var hora:String,
    var fecha:String,
    var winner:Boolean
    ): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeString(eq1)
        parcel.writeString(eq2)
        parcel.writeString(eq1Score)
        parcel.writeString(eq2Score)
        parcel.writeString(hora)
        parcel.writeString(fecha)
        parcel.writeByte(if (winner) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PartidoXEquipoDTO> {
        override fun createFromParcel(parcel: Parcel): PartidoXEquipoDTO {
            return PartidoXEquipoDTO(parcel)
        }

        override fun newArray(size: Int): Array<PartidoXEquipoDTO?> {
            return arrayOfNulls(size)
        }
    }
}