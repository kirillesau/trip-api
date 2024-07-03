drop table if exists TRIP_BOOKING;
drop table if exists TRIP;
drop table if exists TRIP_TYPE;
create table TRIP_TYPE
(
    ID    SERIAL PRIMARY KEY,
    NAME  varchar(255) not null,
    COLOR varchar(255)
);

create table TRIP
(
    ID                SERIAL PRIMARY KEY,
    NAME              varchar(255) not null,
    CITY              varchar(255)     default '',
    DISCOUNT          integer          default 0,
    TYPE_ID           integer      not null,
    LINK              varchar(255)     default '',
    DESCRIPTION       varchar(255)     default '',
    PERFORMANCE       varchar(255)     default '',
    ADDITIONAL_INFO   varchar(255)     default '',
    VALIDITY          varchar(255)     default '',
    PRICE             double precision default 0,
    ALTERNATIVE_PRICE varchar(255)     default '',
    FAVORITE          boolean          default false,
    constraint FK_TRIP_TYPE foreign key (TYPE_ID) references TRIP_TYPE (ID)
);

create table TRIP_BOOKING
(
    ID           SERIAL PRIMARY KEY,
    BOOKING_DATE date    not null default CURRENT_DATE,
    TRIP_ID      integer not null,
    constraint FK_TRIP foreign key (TRIP_ID) references TRIP (ID)
);