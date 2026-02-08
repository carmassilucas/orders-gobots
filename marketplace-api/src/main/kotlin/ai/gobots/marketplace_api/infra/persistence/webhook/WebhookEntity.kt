package ai.gobots.marketplace_api.infra.persistence.webhook

import ai.gobots.marketplace_api.core.domain.Webhook
import ai.gobots.marketplace_api.infra.persistence.store.StoreEntity
import jakarta.persistence.*

@Entity
@Table(name = "tb_webhook")
data class WebhookEntity(

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "seq_webhook_id"
    )
    @SequenceGenerator(
        name = "seq_webhook_id",
        sequenceName = "seq_webhook_id",
        allocationSize = 1
    )
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    val id: Long?,

    @Column(name = "callback_url", unique = true, nullable = false, updatable = false)
    val callbackUrl: String,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "tb_webhook_store",
        joinColumns = [JoinColumn(name = "webhook_id")],
        inverseJoinColumns = [JoinColumn(name = "store_id")]
    )
    val stores: MutableSet<StoreEntity>
) {

    constructor(webhook: Webhook) : this(
        id = webhook.id,
        callbackUrl = webhook.callbackUrl,
        stores = webhook.stores.map {
            StoreEntity(it)
        }.toMutableSet()
    )

    fun toDomain(): Webhook {
        val storesDomain = stores.map {
            it.toDomain()
        }.toMutableSet()
        return Webhook(id, callbackUrl, storesDomain)
    }
}
