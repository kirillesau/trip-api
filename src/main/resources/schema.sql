drop table if exists TRIP;
drop sequence if exists S_TRIP_ID;

create table TRIP
(
    ID              SERIAL,
    NAME            varchar(255) not null,
    HALF_PRICE      boolean default false,
    TYPE            varchar(255),
    LINK            varchar(255),
    DESCRIPTION     varchar(255),
    PERFORMANCE     varchar(255),
    ADDITIONAL_INFO varchar(255),
    VALID_DATE      date,
    PRICE           double precision,
    TAKEN           boolean default false
);

create sequence S_TRIP_ID start with 1;