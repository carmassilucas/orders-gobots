package ai.gobots.marketplace_api.core.repository

import ai.gobots.marketplace_api.core.domain.Order

interface OrderRepository {

    fun save(order: Order)
}