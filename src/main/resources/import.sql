use logistics;
drop table if exists corporation;
drop table if exists car;
drop table if exists goods;
drop table if exists inform;
drop table if exists knowledge;
drop table if exists dynamicinfo;
drop table if exists member;

create table member(
id int primary key auto_increment,
name varchar(20) default '',
sex varchar(6) default '',
email varchar(30) default '',
phone varchar(11) default '',
pwd varchar(20) default '',
question varchar(30) default '',
answer varchar(30) default ''
);
create table corporation(
id int primary key auto_increment,
name varchar(20) default '',
address varchar(30) default '',
phone varchar(11) default '',
link varchar(30) default '',
date varchar(30) default '',
info varchar(255) default '',
mem_id int,
foreign key(mem_id) references member(id)
);
create table goods(
id int primary key auto_increment,
name varchar(20) default '',
type varchar(20) default '',
weight varchar(20) default '',
start varchar(20) default '',
end varchar(20) default '',
date varchar(30) default '',
info varchar(255) default '',
mem_id int,
foreign key(mem_id) references member(id)
);
create table car(
id int primary key auto_increment,
title varchar(20) default '',
count varchar(4) default '',
capacity varchar(4) default '',
start varchar(20) default '',
date varchar(30) default '',
info varchar(255) default '',
mem_id int,
foreign key(mem_id) references member(id)
);
create table knowledge(
id int primary key auto_increment,
title varchar(20) default '',
date varchar(30) default '',
info varchar(255) default '',
mem_id int,
foreign key(mem_id) references member(id)
);
create table dynamicinfo(
id int primary key auto_increment,
title varchar(20) default '',
date varchar(30) default '',
info varchar(255) default '',
mem_id int,
foreign key(mem_id) references member(id)
);
create table inform(
id int primary key auto_increment,
title varchar(20) default '',
date varchar(30) default '',
info varchar(255) default ''
);

insert into member values(1,'triski','男','1406448399@qq.com','13660000000','0000','hello world','hello china');
insert into corporation values (1,'陈氏集团','中国华南地区','020888888','https://github.com/free-myself','2018-09-08 23:58:04','欢迎骚扰',1);
insert into goods values (1,'苹果','水果','2','广州市','北京市','2018-09-08 23:57:02','轻拿轻放',1);
insert into car values (1,'超级货运','10','20','北京市','2018-09-08 23:56:14','速度第一',1);
insert into knowledge values (1,'2012','2018-09-08 23:58:40','世界末日',1);
insert into dynamicinfo values (1,'2012','2018-09-08 23:58:40','世界末日',1);
insert into inform values (1,'2012','2018-09-08 23:52:30','世界末日\r\n终嫣');

