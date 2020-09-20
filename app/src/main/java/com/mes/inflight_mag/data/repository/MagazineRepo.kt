package com.mes.inflight_mag.data.repository

import com.mes.inflight_mag.data.db.model.Magazine
import com.mes.inflight_mag.data.db.model.MagazineRequest
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse

interface MagazineRepo {

    suspend fun fetchMagazines(request: MagazineRequest): NetworkResponse<List<Magazine>, Error>
    suspend fun getAirlineMagazines(airlineId:String): List<Magazine>
    suspend  fun saveMagazine(magazine: Magazine)
    suspend fun getMagazine(magazineId:String): Magazine
    suspend fun checkMagazineExists(magazineId: String): Boolean
}