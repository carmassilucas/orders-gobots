package ai.gobots.marketplace_api.core.repository

import ai.gobots.marketplace_api.core.domain.Status

interface StatusRepository {

    fun saveAll(statuses: List<Status>)
}