create table products
(

    id    bigint       not null auto_increment,
    name varchar(100) not null,
    description varchar(255) not null,
    price decimal not null,
    quantity integer not null,

    primary key (id)

);