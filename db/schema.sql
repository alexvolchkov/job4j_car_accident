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

create view show_all_accident_with_rule as
select a.name accident_name,
       a.text,
       a.address,
       at.name accident_type_name,
       r.name rule_name
from accident a
         left join accident_type at on at.id = a.type_id
join accident_rule ar on a.id = ar.accident_id
join rule r on r.id = ar.rule_id;