
create database test;



create table t_user(
  	id int primary key not null  AUTO_INCREMENT,
  	uname varchar(20) not null,
	pwd varchar(50) not null,
	cnname varchar(8),	
	createdate date ,	
	sex varchar(2)		
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8

create table t_info(
  	id int primary key not null  AUTO_INCREMENT,
  	fid varchar(20),
  	ages varchar(3),		
	mobile varchar(15),	
	addr varchar(100),	
	email varchar(50)	
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8