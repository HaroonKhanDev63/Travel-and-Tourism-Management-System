create database travelandtourismmanagementsystem;

use travelandtourismmanagementsystem;

show databases;

create table account(username varchar(20), 
name varchar(20),
 password varchar(20),
 security varchar(100), 
 answer varchar(50)
 );
 
 show tables;
 
 select * from account;
 
 create table customer(username varchar(20), 
 id varchar(20),
 number varchar(30),
 name varchar(30), gender varchar(30), country varchar(30), address varchar(50),
 phone varchar(15), email varchar(40));
 
 select * from customer;

create table bookpackage(username varchar(20), package varchar(30), person varchar(20),
 id varchar(30), number varchar(30), phone varchar(20), price varchar(20));
 
 select * from bookpackage;
 
 create table hotel(name varchar(30), costperperson varchar(20), acroom varchar(10),
 foodincluded varchar(10));
 
 insert into hotel values('Raddison Blue Hotel' ,'3400', '1000', '1700');
insert into hotel values('River view Hotel' ,'2400', '1600', '1000');
insert into hotel values('The Taj  Hotel' ,'4800', '1290', '2900');

select * from hotel;

create table bookhotel(username varchar(20), name varchar(30), persons varchar(10), 
days varchar(10), ac varchar(10), food varchar(10), id varchar(20), number varchar(20),
phone varchar(20), price varchar(20));
select * from bookhotel;
