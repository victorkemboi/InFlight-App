package com.mes.inflight_mag.data.repository.data_src

import com.mes.inflight_mag.data.api.ApiService
import com.mes.inflight_mag.data.db.dao.MagazineDao
import com.mes.inflight_mag.data.db.model.Magazine
import com.mes.inflight_mag.data.db.model.MagazineRequest
import com.mes.inflight_mag.data.repository.MagazineRepo
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse

class MagazineDataSrc(
    private val apiService: ApiService,
    private val magazineDao: MagazineDao
):MagazineRepo {
    override suspend fun fetchMagazines(request: MagazineRequest): NetworkResponse<List<Magazine>, Error> {
        return  apiService.getMagazines(request)
    }

    override suspend fun getAirlineMagazines(airlineId: String): List<Magazine> {
        return magazineDao.getAirlineMagazines(airlineId)
    }

    override suspend fun saveMagazine(magazine: Magazine) {
        magazineDao.insertMagazine(magazine)
    }

    override suspend fun getMagazine(magazineId: String): Magazine {
        return magazineDao.getMagazine(magazineId)
    }

    override suspend fun checkMagazineExists(magazineId: String): Boolean {
        return when( magazineDao.checkMagazineExists(magazineId)){
            0 -> false
            else -> true
        }
    }

    override suspend fun getAirlineMagCount(airlineId: String): Int {
        return magazineDao.getAirlineMagazines(airlineId).size
    }
}