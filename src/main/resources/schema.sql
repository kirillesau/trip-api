drop table if exists TRIP;

drop sequence if exists S_TRIP_ID;

create table TRIP
(
    ID   integer identity primary key,
    NAME varchar(50) not null
);

create sequence S_TRIP_ID start with 1;