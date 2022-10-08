create table orders
(
    buyId       int          not null,
    goodsIds    varchar(255) not null,
    sellId      int          not null,
    time        date         not null,
    arriveOrNot tinyint(1)   not null,
    destination varchar(255) not null,
    id          int auto_increment
        primary key
);


