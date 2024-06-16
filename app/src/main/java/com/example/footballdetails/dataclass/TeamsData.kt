package com.example.footballdetails.dataclass

import android.os.Parcel
import android.os.Parcelable

data class TeamsData (
    val id: Int,
    val sport_id: Int,
    val country_id: Int,
    val gender: String,
    val name: String,
    val short_code: String,
    val image_path: String,
    val type: String,

val last_played_at:String


    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(sport_id)
        parcel.writeInt(country_id)
        parcel.writeString(gender)
        parcel.writeString(name)
        parcel.writeString(short_code)
        parcel.writeString(image_path)
        parcel.writeString(type)
        parcel.writeString(last_played_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TeamsData> {
        override fun createFromParcel(parcel: Parcel): TeamsData {
            return TeamsData(parcel)
        }

        override fun newArray(size: Int): Array<TeamsData?> {
            return arrayOfNulls(size)
        }
    }
}

/*
{
            "id": 85,
            "sport_id": 1,
            "country_id": 320,
            "venue_id": 5655,
            "gender": "male",
            "name": "FC Copenhagen",
            "short_code": "COP",
            "image_path": "https://cdn.sportmonks.com/images/soccer/teams/21/85.png",
            "founded": 1992,
            "type": "domestic",
            "placeholder": false,
            "last_played_at": "2024-05-31 17:00:00"
        },
   */