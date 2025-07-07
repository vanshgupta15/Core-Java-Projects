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

insert into products(item,price,catg_master_id,quantity)
values('Lays Onion Flavour',20,1,5);
insert into products(item,price,catg_master_id,quantity)
values('Too Yumm Multigrain',29,1,7);
insert into products(item,price,catg_master_id,quantity)
values('Kurkure Masala',20,1,8);
insert into products(item,price,catg_master_id,quantity)
values('Pepsi 400ml',20,2,10);

INSERT INTO products(item, price, catg_master_id, quantity)
VALUES ('Lays Classic', 20, 
       (SELECT id FROM category_master WHERE category = 'Snacks' AND sub_category = 'Chips'),
        15);


UPDATE products 
SET item = 'Reynolds Gel Pen',
    price = 10,
    catg_master_id = (
        SELECT id FROM category_master 
        WHERE category = 'Stationery' AND sub_category = 'Pen'
    ),
    quantity = 9
WHERE id = 5;


delete from products where id=1;

insert into users(username,password,role,is_active)
values('vansh','vansh1512','customer',1);
