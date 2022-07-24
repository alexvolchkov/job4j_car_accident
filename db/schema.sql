create table if not exists accident_type (
id serial primary key,
name varchar(2000)
);

create table if not exists accident (
id serial primary key,
name varchar(2000),
text varchar(2000),
address varchar(1000),
type_id int not null references accident_type(id)
);

create table if not exists rule (
id serial primary key,
name varchar(2000)
);

create table if not exists accident_rule (
accident_id int not null references accident(id),
rule_id int not null references rule(id),
primary key(accident_id, rule_id)
);