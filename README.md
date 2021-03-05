# spring-reactive-demo

##1. DB
###1.1. postgis
####1.1.1. install 
   
> docker pull postgis/postgis  
> docker run -p 5432:5432 --name postgis -e POSTGRES_PASSWORD=mysecretpassword -d postgis/postgis

####1.1.2. init database
+ app
  > CREATE DATABASE APP TEMPLATE TEMPLATE_POSTGIS;
  > 
  > CREATE USER app_user PASSWORD 'app12341234';
  > 
  > ALTER DATABASE APP OWNER TO app_user;

+ demo 
  > CREATE DATABASE DEMO TEMPLATE TEMPLATE_POSTGIS;
  >
  > CREATE USER demo_user PASSWORD 'demo12341234';
  >
  > ALTER DATABASE DEMO OWNER TO demo_user;

+ quartz
  > CREATE DATABASE QUARTZ TEMPLATE TEMPLATE0;
  >
  > CREATE USER quartz_user PASSWORD 'quartz12341234';
  >
  > ALTER DATABASE QUARTZ OWNER TO quartz_user;

+ show database owner
  > SELECT d.datname as "Name",  
  > pg_catalog.pg_get_userbyid(d.datdba) as "Owner"  
  > FROM pg_catalog.pg_database d

###1.2. Redis
####1.2.1. install 

> docker pull redis  
> docker run --name some-redis -d redis

## jpa

## r2dbc
## quartz
