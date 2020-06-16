create table book (
    id bigint not null,
    author varchar(255) not null,
    description text(65535) not null,
    filename varchar(255) not null,
    genre varchar(255) not null,
    isbn varchar(255) not null,
    name varchar(255) not null,
    year integer not null,
    primary key (id)
) engine=InnoDB;

create table hibernate_sequence (
    next_val bigint
) engine=InnoDB;

insert into hibernate_sequence values ( 1 );

insert into hibernate_sequence values ( 1 );

create table user_lib (
    user_id bigint not null,
    lib_books_id bigint
) engine=InnoDB;

create table user_orders (
    user_id bigint not null,
    ordered_books_id bigint
) engine=InnoDB;

create table user_role (
    user_id bigint not null,
    roles varchar(255)
) engine=InnoDB;

create table usr (
    id bigint not null,
    activation_code varchar(255),
    active boolean not null,
    email varchar(255) not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
) engine=InnoDB;

alter table user_lib
    add constraint user_lib_fk
    foreign key (user_id) references usr (id);

alter table user_orders
    add constraint user_orders_fk
    foreign key (user_id) references usr (id);

alter table user_role
    add constraint user_role_fk
    foreign key (user_id) references usr (id);