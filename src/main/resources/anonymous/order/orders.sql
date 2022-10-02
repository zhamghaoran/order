create table `order`.orders
(
    buyId       int        not null,
    goodsId     int        not null,
    sellId      int        not null,
    time        date       not null,
    arriveOrNot tinyint(1) not null
);

