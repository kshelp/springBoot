-- 1. oracle db인 경우
-- SCOTT/TIGER@XE로 로그인한 후,
create table news (
	title varchar(100),
	journalist varchar(30),
	reg_dt TIMESTAMP,
	publisher varchar(30)
);

insert into news values('제목1', '홍길동', SYSDATE, '조선일보');
insert into news values('제목2', '유재석', SYSDATE, '한국일보');
commit;
select * from news;


-- 2. mysql인 경우
-- root 계정으로 설정
create database bootex default character set utf8;

create user 'bootuser'@'localhost' identified by 'bootuser';
grant all privileges on bootex.* to 'bootuser'@'localhost';

create user 'bootuser'@'%' identified by 'bootuser';
grant all privileges on bootex.* to 'bootuser'@'%';


-- bootuser 계정으로 설정
use bootex;
create table news (
	title varchar(100),
	journalist varchar(30),
	reg_dt TIMESTAMP NOT NULL DEFAULT now(),
	publisher varchar(30)	
);

insert into news (title,journalist,publisher) values('제목1', '홍길동', '조선일보');
insert into news (title,journalist,publisher) values('제목2', '유재석', '한국일보');
select * from news;