create table user (
    id BIGINT primary key  AUTO_INCREMENT,
    username varchar(255) not null,
    email varchar(255) unique NOT NULL ,
    pawssord VARCHAR(255) NOT NULL
)