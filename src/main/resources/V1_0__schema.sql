create table books
(
    id       UUID DEFAULT  gen_random_uuid() PRIMARY KEY,
    title    varchar(100) not null,
    author   varchar(50) not null,
    year     INTEGER not null
        check ( year> 1000 AND year<2025 ),
    country varchar(50),
    rate     INTEGER default 1
        check ( rate>=1 AND rate<=5 )
)