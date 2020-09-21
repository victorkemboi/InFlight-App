package com.mes.inflight_mag.data.repository


import com.mes.inflight_mag.data.db.model.Airline
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse

interface AirlineRepo {
    suspend fun fetchAirlines(): NetworkResponse<List<Airline>, Error>
    suspend fun getAirlines(): List<Airline>
    suspend fun save(airline: Airline)
    suspend fun checkExists(airlineId:String): Boolean
}