create table tb_store (
    id uuid not null,
    name character varying not null
);

alter table tb_store
    add constraint pk_tb_store primary key (id);

insert into tb_store
    values ('894678dd-1a6d-4222-bec7-58c710a6ee07', 'Mercado Livre'),
           ('8ad3072a-242b-42ff-b7a7-23e1a986abe2', 'Amazon Brasil');
