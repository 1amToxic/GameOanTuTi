create database testdbgame;
use testdbgame;
drop table tblaccount;
drop table tblchoice;
drop table tbluser;
create table tblaccount(id integer primary key auto_increment, _username varchar(20), _password varchar(20));
create table tbluser(id integer primary key auto_increment,_accid integer, _point integer, _status boolean,
					foreign key(_accid) references tblaccount(id));
create table tblgame(id integer primary key,_timecreate date);
create table tblchoice(id integer primary key, _userid integer, _gameid integer, choice integer,
					foreign key(_userid) references tbluser(id), foreign key(_gameid) references tblgame(id));
insert into tblaccount(_username,_password) values('1','1'), ('2','2');
insert into tbluser(_accid,_point,_status) values(1,0,false),(2,0,false);
