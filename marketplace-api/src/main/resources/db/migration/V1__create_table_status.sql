create table tb_status (
    id bigint not null,
    status character varying not null
);

alter table tb_status
    add constraint pk_tb_status primary key (id);

alter table tb_status
    add constraint uc_tb_status_status unique (status);
