package com.mes.inflight_mag.data.repository.data_src

import com.mes.inflight_mag.data.api.ApiService
import com.mes.inflight_mag.data.db.dao.AirlineDao
import com.mes.inflight_mag.data.db.model.Airline
import com.mes.inflight_mag.data.repository.AirlineRepo
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse

class AirlineDataSrc(
    private val apiService: ApiService,
    private val airlineDao: AirlineDao
):AirlineRepo {
    override suspend fun fetchAirlines(): NetworkResponse<List<Airline>, Error> {
        return apiService.getAirlines()
    }

    override suspend fun getAirlines(): List<Airline> {
        return airlineDao.getAirlines()
    }
}