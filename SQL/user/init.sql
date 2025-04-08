create table contest_user (
    id varchar(36),
    nickname text check(length(nickname) <=100) not null,
    fullname text check (length(fullname) <= 200) default 'anonymous user',
    organization text check(length(organization) <=500),
    email text check(length(email) <= 200) unique not null,
    create_at timestamp default now(),
    update_at timestamp default now(),
    last_login timestamp default now(),
    total_attempt integer default 0 check(total_attempt >= 0)
);
