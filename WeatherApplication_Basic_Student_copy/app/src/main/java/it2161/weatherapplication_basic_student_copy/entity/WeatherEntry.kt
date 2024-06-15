package it2161.weatherapplication_basic_student_copy.entity
import android.content.Context

/**
 * Created by keeliren on 10/3/18.
 */

data class WeatherEntry(val date:String = "", val weatherID: Int = 0, val tempMax: Double = 0.0, val tempMin: Double = 0.0,
                        val humidity:Float = 0.0f, val pressure: Float = 0.0f, val windspeed: Float = 0.0f, val degrees:Float = 0.0f ){


    override fun toString(): String {



        return "Date :" + date + "\n" +
                "weatherID :" +  weatherID + "\n"+
                "tempMax :" + tempMax + "\n"+
                "tempMin :" + tempMin + "\n" +
                "humidity :" + humidity + "\n"+
                "pressure :" + pressure + "\n"+
                "windspeed :" + windspeed +"\n"+
                "degrees :" + degrees

    }

}