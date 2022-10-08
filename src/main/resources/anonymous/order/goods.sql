use `order`;
create table goods
(
    name        varchar(30)  not null,
    price       int          not null,
    description varchar(300) null,
    picture     longblob     null,
    id          int auto_increment
        primary key,
    belong      int          not null,
    constraint id
        unique (id)
);

