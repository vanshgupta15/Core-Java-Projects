use Kiit_Kirana_Store;

create table category_master
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


insert into category_master(category,sub_category) 
values('Snacks','Chips');
insert into category_master(category,sub_category)
values('Snacks','Cold Drink');
insert into category_master(category,sub_category)
values('Snacks','Biscuit');

insert into products(item,price,catg_master_id,quantity)
values('Lays Onion Flavour',20,1,5);
insert into products(item,price,catg_master_id,quantity)
values('Too Yumm Multigrain',29,1,7);
insert into products(item,price,catg_master_id,quantity)
values('Kurkure Masala',20,1,8);
insert into products(item,price,catg_master_id,quantity)
values('Pepsi 400ml',20,2,10);