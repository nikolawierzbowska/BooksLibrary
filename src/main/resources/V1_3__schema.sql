Drop table books;

create table books
(
    id       UUID DEFAULT  gen_random_uuid() PRIMARY KEY,
    title    varchar(100) not null,
    author   varchar(50) not null,
    year     INTEGER not null
        check ( year> 1000 AND year<2025 ),
    country varchar(50),
    rate     INTEGER default Null
        check ( rate>=1 AND rate<=5 )
);

insert into books(title, author, year, country)
VALUES
    ('Obcy', 'Albert Camus', 1942,'Francja'),
    ('W poszukiwaniu straconego czasu', 'Marcel Proust', 1927,'Francja'),
    ('Przeminęło z wiatrem', 'Margaret Mitchell', 1936,'USA'),
    ('Wielki Gatsby', 'Francisa Scotta Fitzgeralda', 1925,'USA'),
    ('Lalka', 'Bolesław Prus', 1890,'Polska'),
    ('Noce i dnie', 'Maria Dąbrowska', 1931,'Polska'),
    ('Rebelia', 'Davis Siobhan', 2022,'Anglia'),
    ('Dżuma', 'Albert Camus', 1925,'Francja'),
    ('Kamizelka', 'Bolesław Prus', 1882,'Polska')

