

drop table if exists authorities_new;
drop table if exists users_new;


 
  create table users_new(
      username varchar_ignorecase(50) not null primary key,
      password varchar_ignorecase(50) not null,
      enabled boolean not null);

  create table authorities_new (
      username varchar_ignorecase(50) not null,
      authority varchar_ignorecase(50) not null,
      constraint fk_authorities_users_new foreign key(username) references users_new(username));
      
      create unique index ix_auth_username_new on authorities_new (username,authority);