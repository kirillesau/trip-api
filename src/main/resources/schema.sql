drop table if exists TRIP_BOOKING;
drop table if exists TRIP;
drop table if exists TRIP_TYPE;
create table TRIP_TYPE
(
    ID   SERIAL PRIMARY KEY,
    NAME varchar(255) not null
);

create table TRIP
(
    ID              SERIAL PRIMARY KEY,
    NAME            varchar(255) not null,
    DISCOUNT        integer default 0,
    TYPE_ID         integer      not null,
    LINK            varchar(255),
    DESCRIPTION     varchar(255),
    PERFORMANCE     varchar(255),
    ADDITIONAL_INFO varchar(255),
    VALID_DATE      date,
    PRICE           double precision,
    constraint FK_TRIP_TYPE foreign key (TYPE_ID) references TRIP_TYPE (ID)
);

create table TRIP_BOOKING
(
    ID           SERIAL PRIMARY KEY,
    BOOKING_DATE date,
    TRIP_ID      integer,
    constraint FK_TRIP foreign key (TRIP_ID) references TRIP (ID)
);