create table tb_order (
    id uuid not null,
    store_id uuid not null,
    amount decimal not null,
    status_id bigint not null
);

alter table tb_order 
    add constraint pk_tb_order primary key (id);

ALTER TABLE tb_order
    add constraint FK_TB_ORDER_ON_STATUS foreign key (status_id) references tb_status (id);

ALTER TABLE tb_order
    add constraint FK_TB_ORDER_ON_STORE foreign key (store_id) references tb_store (id);
