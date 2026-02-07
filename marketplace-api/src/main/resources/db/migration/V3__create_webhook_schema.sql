create sequence if not exists seq_webhook_id start with 1 increment by 1;

create table tb_webhook (
    id bigint not null default nextval('seq_webhook_id'),
    callback_url character varying not null
);

alter table tb_webhook
    add constraint pk_tb_webhook primary key (id);

alter table tb_webhook
    add constraint uc_tb_webhook_callback_url unique (callback_url);

create table tb_webhook_store (
    store_id   uuid   not null,
    webhook_id bigint not null
);

alter table tb_webhook_store
    add constraint uc_tb_webhook_store unique (store_id, webhook_id);

alter table tb_webhook_store
    add constraint fk_tb_webhook_store_on_webhook foreign key (webhook_id) references tb_webhook (id);

alter table tb_webhook_store
    add constraint fk_tb_webhook_store_on_store foreign key (store_id) references tb_store (id);
