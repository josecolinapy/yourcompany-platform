create table if not exists item (
    id uuid not null,
    version bigint not null,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone,
    name varchar(150) not null,
    description varchar(500),
    status varchar(30) not null,
    primary key (id)
);
