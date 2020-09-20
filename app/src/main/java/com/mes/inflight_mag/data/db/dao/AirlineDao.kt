package com.mes.inflight_mag.data.db.dao

import androidx.room.*
import com.mes.inflight_mag.data.db.model.Airline

@Dao
interface AirlineDao {
    @Insert
    fun insertAirline(airline: Airline): Long

    @Delete
    fun deleteAirline(airline: Airline)

    @Update
    fun updateUser(airline: Airline): Int

    @Query("SELECT COUNT() FROM airline WHERE airline_id=:id  ")
    fun checkAirlineExists(id:String): Int

    @Query("SELECT * FROM airline WHERE airline_id=:id   LIMIT  1")
    fun getAirline(id:String): Airline

    @Query("SELECT * FROM airline ")
    fun getAirlines(): List<Airline>
}