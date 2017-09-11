Use dmaa0916_202069;

--Create new tables for database
create table PersonDB(
id int identity(1,1),
fName varchar(255) not null,
lName varchar(255) not null,
address varchar(255) not null,
city varchar(255) not null,
zipCode varchar(20) not null,
phone varchar(11) not null,
email varchar(255) not null,
PRIMARY KEY (id)
);

create table SectionDB(
id int identity(1,1),
sectionName varchar(255) not null,
amountOfEmployees int not null,
PRIMARY KEY (id)
);

create table EmployeeDB(
id int identity(1,1),
jobTitle varchar(255) not null,
PersonDB_personId int not null,
SectionDB_sectionId int not null,
PRIMARY KEY (id),
FOREIGN KEY (PersonDB_personId) REFERENCES PersonDB(id) ON DELETE CASCADE,
FOREIGN KEY (SectionDB_sectionID) REFERENCES SectionDB(id) ON DELETE CASCADE
);

create table CustomerCategoryDB(
id int identity(1,1),
categoryName varchar(255) not null,
primary key(id)
);

create table CustomerDB(
id int identity(1,1),
type varchar(255) not null,
CustomerCategoryDB_categoryId int not null,
cvr varchar(255) not null,
companyName varchar(255) not null,
PersonDB_personId int not null,
PRIMARY KEY (id),
FOREIGN KEY(PersonDB_personId) REFERENCES PersonDB(id) ON DELETE CASCADE,
FOREIGN KEY(CustomerCategoryDB_CategoryId) REFERENCES CustomerCategoryDB(id) ON DELETE CASCADE
);

create table IngredientsDB(
id int identity(1,1),
name varchar(255) not null,
price float(10) not null,
quantity int not null,
monthlyConsumption int,
minQty int,
shippingCost int,
reorderActivate bit, -- To set if ingredient needs auto reorder or not
reorderQuantity int,
primary key(id)
);


create table RecipeDB(
id int identity(1,1),
name varchar(255) not null,
description varchar(8000) not null,
primary key (id)
);


create table RecipeLineItemDB(
id int identity (1,1),
quantity int not null,
IngredientsDB_ingredientsId int not null,
RecipeDB_recipeId int not null,
primary key(id),
foreign key (IngredientsDB_ingredientsId) references IngredientsDB(id) ON DELETE CASCADE,
foreign key (RecipeDB_recipeId) references RecipeDB(id)ON DELETE CASCADE
);



create table ProductDB(
id int identity(1,1),
name varchar(255) not null,
price float(10) not null,
totalQty int not null,
description varchar(255) not null,
details varchar(255) not null,
boxQuantity int,
RecipeDB_recipeId int,
primary key (id),
foreign key(RecipeDB_recipeId) references RecipeDB(id) ON DELETE CASCADE
);

create table CompanyDB(
id int identity(1,1),
name varchar(255) not null,
address varchar(255) not null,
city varchar(255) not null,
zipCode varchar(255) not null,
cvr varchar(8),
phone varchar(25) not null,
email varchar(255) not null,
primary key(id), 
);

create table OfferDB(
id int identity(1,1),
startDate varchar(255),
endDate varchar(255),
discountPrice float(20),
totalPrice float(20),
primary key(id)
);

create table OfferLineItemDB(
id int identity(1,1),
quantity int,
price float(20),
ProductDB_productId int,
OfferDB_offerId int,
primary key(id),
foreign key (ProductDB_productId) references ProductDB(id) ON DELETE CASCADE,
foreign key(OfferDB_offerId) references OfferDB(id) ON DELETE CASCADE
);

create table OrderDB(
id int identity(1,1),
totalPrice float(20),
tax float(20),
date varchar(255),
status int,
discountPrice float(20),
CustomerDB_customerId int,
EmployeeDB_employeeId int,
CompanyDB_companyId int,
primary key(id),
foreign key(CustomerDB_customerId) references CustomerDB(id),
foreign key(EmployeeDB_employeeId) references EmployeeDB(id),
foreign key(CompanyDB_companyId) references CompanyDB(id)
);

create table SaleLineItemDB(
id int identity(1,1),
quantity int,
price float(20) not null,
ProductDB_productId int,
OrderDB_orderId int,
primary key(id),
foreign key(ProductDB_productId) references ProductDB(id) ON DELETE CASCADE,
foreign key(OrderDB_orderId) references OrderDB(id) ON DELETE CASCADE
);







