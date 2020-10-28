create table user
(
    user_id   bigint auto_increment
        primary key,
    address   varchar(255) null,
    user_age  int          not null,
    user_name varchar(255) null
)
    engine = MyISAM;