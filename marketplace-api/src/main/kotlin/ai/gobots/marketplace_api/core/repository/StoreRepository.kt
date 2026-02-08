package ai.gobots.marketplace_api.core.repository

import ai.gobots.marketplace_api.core.domain.Store
import java.util.UUID

interface StoreRepository {

    fun findById(id: UUID): Store?
}