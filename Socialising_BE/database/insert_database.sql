use shoestore;

insert into role(code,name) values('ADMIN','Quản trị hệ thống');
insert into role(code,name) values('USER','người dùng');

insert into users(username,password,fullname,status)
values('admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','admin',1);
insert into users(username,password,fullname,status)
values('nguyenvana','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van a',1);

INSERT INTO user_role(user_id,role_id) VALUES (1,1);
INSERT INTO user_role(user_id,role_id) VALUES (2,2);

INSERT INTO brand(name, code) VALUES ('nike','NIKE');
INSERT INTO brand(name, code) VALUES ('adidas','ADIDAS');

INSERT INTO product(content,name,numberofsell,price,pricedescription,shortdescription,thumbnail,brandid) VALUES ('ADIDAS COPA 20.1 TF – EH0893 – XANH / TRẮNG','ADIDAS COPA 20.1 TF – EH0893 – XANH / TRẮNG',10,2750000,'2,750,000 ₫','ADIDAS COPA 20.1 TF – EH0893 – XANH / TRẮNG','01.jpg',2);
INSERT INTO product(content,name,numberofsell,price,pricedescription,shortdescription,thumbnail,brandid) VALUES ('NIKE MERCURIAL SUPERFLY 7 ACADEMY MDS TF – BQ5435-703 – VÀNG / CHANH','NIKE MERCURIAL SUPERFLY 7 ACADEMY MDS TF – BQ5435-703 – VÀNG / CHANH',12,2150000,'2,150,000 ₫','NIKE MERCURIAL SUPERFLY 7 ACADEMY MDS TF – BQ5435-703 – VÀNG / CHANH','02.jpg',1);
INSERT INTO product(content,name,numberofsell,price,pricedescription,shortdescription,thumbnail,brandid) VALUES ('ADIDAS TOPSALA – FV2551 – XANH HOÀNG GIA','ADIDAS TOPSALA – FV2551 – XANH HOÀNG GIA',12,1750000,'1,750,000 ₫','ADIDAS TOPSALA – FV2551 – XANH HOÀNG GIA','03.jpg',2);
INSERT INTO product(content,name,numberofsell,price,pricedescription,shortdescription,thumbnail,brandid) VALUES ('NIKE REACT GATO IC – CT0550-474 – XANH HOÀNG GIA','NIKE REACT GATO IC – CT0550-474 – XANH HOÀNG GIA',13,2200000,'2,200,000 ₫','NIKE REACT GATO IC – CT0550-474 – XANH HOÀNG GIA','04.jpg',1);










