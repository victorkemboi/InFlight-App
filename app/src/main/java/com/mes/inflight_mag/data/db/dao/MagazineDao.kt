package com.mes.inflight_mag.data.db.dao

import androidx.room.*
import com.mes.inflight_mag.data.db.model.Magazine

@Dao
interface MagazineDao {
    @Insert
    fun insertMagazine(magazine: Magazine): Long

    @Delete
    fun deleteMagazine(magazine: Magazine)

    @Update
    fun updateMagazine(magazine: Magazine): Int

    @Query("SELECT * FROM magazine  ")
    fun getMagazines(): List<Magazine>

    @Query("SELECT * FROM magazine where airline_id=:airlineId")
    fun getAirlineMagazines(airlineId:String): List<Magazine>

    @Query("SELECT * FROM magazine where magazine_id=:magazine_id  LIMIT  1" )
    fun getMagazine(magazine_id:String): Magazine

    @Query("SELECT COUNT() FROM magazine WHERE magazine_id=:magazineId  ")
    fun checkMagazineExists(magazineId:String): Int
}