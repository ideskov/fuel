create table fuel.records
(
    id                uuid primary key,
    distance_in_miles int  not null,
    litres            int  not null,
    "from"            date not null,
    "to"              date not null
);
