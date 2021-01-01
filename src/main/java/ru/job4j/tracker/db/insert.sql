create  table if not exists items(
                      id serial primary key,
                      name text
);

insert into items (name) values ('text1'), ('text2'), ('text3');


