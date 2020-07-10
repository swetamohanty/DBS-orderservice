DROP TABLE IF EXISTS orderdetails;  
create table orderdetails
(
   id integer not null,
   customername varchar(255) not null,
   orderdate varchar(255) not null,
   shippingaddress varchar(255) not null,
   orderitems integer not null,
   total long not null,
   primary key(id)
);
