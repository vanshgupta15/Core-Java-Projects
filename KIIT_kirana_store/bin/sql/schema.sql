use Kiit_Kirana_Store;

create create table category_master
(   
	id int AUTO_INCREMENT,
	category VARCHAR(300),
	sub_category VARCHAR(300),
	PRIMARY KEY ( id )
);


create table products
(
	id int AUTO_INCREMENT,
	item VARCHAR(500),
	price int not null,
	catg_master_id int not null,
	quantity int not null,
	PRIMARY KEY ( id ),
	FOREIGN KEY (catg_master_id) REFERENCES category_master(id)
);

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'staff', 'customer') DEFAULT 'customer',
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
);


insert into category_master(category,sub_category) 
values('Snacks','Chips');
insert into category_master(category,sub_category)
values('Snacks','Cold Drink');
insert into category_master(category,sub_category)
values('Snacks','Biscuit');
insert into category_master(category,sub_category) 
values('Stationery','Books');
insert into category_master(category,sub_category) 
values('Stationery','Pen');

insert into products(item,price,catg_master_id,quantity)
values('Lays Onion Flavour',20,1,5);
insert into products(item,price,catg_master_id,quantity)
values('Too Yumm Multigrain',29,1,7);
insert into products(item,price,catg_master_id,quantity)
values('Kurkure Masala',20,1,8);
insert into products(item,price,catg_master_id,quantity)
values('Pepsi 400ml',20,2,10);

insert into users(username,password,role,is_active)
values('kiit123','kiit@123','admin',true);

select * from users where username='kiit123' and password='kiit@123'

select distinct(category) from category_master;

select distinct(sub_category) from category_master where category='Snacks';
select sub_category  from category_master where category='Snacks';