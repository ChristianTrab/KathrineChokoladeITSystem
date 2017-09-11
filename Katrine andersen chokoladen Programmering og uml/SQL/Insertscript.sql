use dmaa0916_202069;

-- PersonDB (fName, lName, adress, city, zipCode, phone, mail)

insert into PersonDB values('Jens', 'Peter Jensen', 'Jensenvej 5', 'Aalborg', '9000', '20189483', 'jpj@jpjteam.dk');			-- Customer
insert into PersonDB values('Ole', 'Olesen', 'Olevej 23', 'Thisted', ' 7700', '59843765', 'Oleolesen@mail.dk');					-- Customer
insert into PersonDB values('Rita', 'Klochs', 'Randersvej 155', 'Randers', '8900', '65487954', 'Ritak@gmail.com');				-- Customer
insert into PersonDB values('Anne', 'Hejlsø', 'Kirkevej 54', 'Risskov', '8240', '29848173', 'AnneHejl@hotmail.dk');				-- Customer
insert into PersonDB values('Lars', 'Mølgaard', 'Hadsundvej 65', 'Vejgaard', '9000', '89652421', 'lm@gmail.com');				-- Customer
insert into PersonDB values('Mark', 'Højler', 'Ritsagvej 123', 'Aalborg', '9000', '89657424', 'Ansat@kac.dk');					-- Employee
insert into PersonDB values('Troels', 'Humleren', 'Bispensgade 211', 'Aalborg', '9000', '12315122', 'ansat@kac.dk');			-- Employee
insert into PersonDB values('Niels', 'Dalleren', 'Østerbro 52', 'Aalborg', '9000', '12302394', 'Ansat@kac.dk');					-- Employee
insert into PersonDB values('Kim', 'Højgaard', 'Hadsundvej 654', 'Vejgaard', '9000', '89247426', 'Ansat@kac.dk');				-- Employee
insert into PersonDB values('Simon', 'Gøjler', 'Hadsundvej 323', 'Vejgaard', '9000', '84257425', 'Ansat@kac.dk');				-- Employee
insert into PersonDB values('Jakob', 'Skovsgaard', 'Adressen 1231', 'Ukendt', '9000', '27515843', 'JakobSkovsgaard@kac.dk');	-- Employee/Boss



-- SectionDB (sectionName, amountOfEmployees)

insert into SectionDB values('Kontor', '3');			-- ID = 1
insert into SectionDB values('Pakkeri', '6');			-- ID = 2
insert into SectionDB values('Produktion', '5');		-- ID = 3
insert into SectionDB values('Reserve(Vikare)', '3');	-- ID = 4


-- EmployeeDB (jobTitle // Associates PersonDB_personId, SectionDB_sectionId)

insert into EmployeeDB VALUES ('Administration', 6, 1);						-- ID = 1
insert into EmployeeDB VALUES ('Kontormedarbejder', 7, 1);					-- ID = 2
insert into EmployeeDB VALUES ('Pakkemedarbejder', 7, (select SectionDB_sectionId from EmployeeDB where id = '1'));	-- ID = 3
insert into EmployeeDB VALUES ('CEO', 11, (select SectionDB_sectionId from EmployeeDB where id = '1'));				-- ID = 4

-- CustomerCategoryDB (categoryName)

insert into CustomerCategoryDB VALUES ('Restaurant');			-- ID = 1
insert into CustomerCategoryDB VALUES ('Hotel');				-- ID = 2
insert into CustomerCategoryDB VALUES ('Bageri');				-- ID = 3
insert into CustomerCategoryDB VALUES ('Butik');				-- ID = 4
insert into CustomerCategoryDB VALUES ('Supermarked');			-- ID = 5
insert into CustomerCategoryDB VALUES ('Online');				-- ID = 6
insert into CustomerCategoryDB VALUES ('Andet');				-- ID = 7

-- CustomerDB (type, CustomerCategoryDB_categoryId, cvr, comanyName, personDB_PersonId)



insert into CustomerDB VALUES ('Privat', 7, '0000000', 'N/A', 1);				-- ID = 1
insert into CustomerDB VALUES ('Erhverv', 1, '20123593', 'Danglterre', 2);		-- ID = 2
insert into CustomerDB VALUES ('Erhverv', 2, '28362718', 'Comwell', 4);		-- ID = 3
insert into CustomerDB VALUES ('Privat', 3, '06859482', 'Kagekompaniet', 5);	-- ID = 4

-- IngredientsDB (name, price, quantitity, 									monthlyConsumption, minQty, shippingCost, reOrderActivate, reOrderQuantity)
--																|C|					|R|	   				|S|																	

insert into IngredientsDB VALUES ('Mørk Chokolade 75% 800g', 	119.00, 42, 15, 25, 150, 1, 16);				-- ID = 1
insert into IngredientsDB VALUES ('Piskefløde 18% 1L', 			12.00, 100, 52, 75, 75, 0, 66);					-- ID = 2
insert into IngredientsDB VALUES ('Sukker 1kg', 				6.00, 400, 	272, 300, 150, 1, 301);				-- ID = 3
insert into IngredientsDB VALUES ('Vaniljesukker 100g', 		12.00, 86, 28, 40, 75, 1, 48);					-- ID = 4
insert into IngredientsDB VALUES ('Æg 5x6(30stk Bakke)', 		25.00, 62, 15, 45, 100, 0, 28);					-- ID = 5
insert into IngredientsDB VALUES ('Odense Marcipan 200g', 		65.00, 55, 45, 100, 100, 1, 30);				-- ID = 6
insert into IngredientsDB VALUES ('Lys Chokolade 35% 500g', 	60.00, 182, 100, 125, 100, 1, 47);				-- ID = 7
insert into IngredientsDB VALUES ('Karamel 1kg', 				49.95, 105, 34, 60, 100, 1, 30);				-- ID = 8
insert into IngredientsDB VALUES ('Hasselnødder 200g', 			16.00, 87, 50, 100, 75, 1, 56);					-- ID = 9
insert into IngredientsDB VALUES ('Smør 200g', 					10.00, 300, 100, 150, 100, 1, 115);				-- ID = 10
insert into IngredientsDB VALUES ('Honning 100g', 				13.95, 72, 30, 80, 75, 1, 46);					-- ID = 11
insert into IngredientsDB VALUES ('Kakaopulver 250g', 			25.00, 42, 35, 45, 100, 1, 43);					-- ID = 12
insert into IngredientsDB VALUES ('Rom 70cl', 					150.00, 20, 25, 25, 100, 1, 10);				-- ID = 13
insert into IngredientsDB VALUES ('Jordbær 150g', 				20.00, 20, 20, 15, 50, 0, 18);					-- ID = 14
insert into IngredientsDB VALUES ('Hvid chokolade 200g', 		30.00, 100, 65, 80, 100, 1, 54);				-- ID = 15
insert into IngredientsDB VALUES ('Glasur 0,5l', 				12.00, 300, 6, 8, 50, 1, 18);					-- ID = 16

-- RecipeDB (name, description, RecipeLineItemDB_recipeLineItemId)

insert into RecipeDB VALUES ('Flødebolle', 'Lav din egen flødebolle');															-- ID = 1
insert into RecipeDB VALUES ('Chokolade Stykke', 'Smelt Chokoladen, rør det hele sammen og klat ud til en stykke chokolade');	-- ID = 2
insert into RecipeDB VALUES ('Marcipan Stykke', 'Smelt Chokoladen, klat det hele sammen');										-- ID = 3
insert into RecipeDB VALUES ('Chokolade Æg', 'Smelt Chokoladen, klask sammen til et æg');										-- ID = 4
insert into RecipeDB VALUES ('Chokoladeknapper', 'Smelt Chokoladen, put det i små forme');										-- ID = 5
insert into RecipeDB VALUES ('Rom kapsel', 'Smelt Chokoladen, tilføj rom og form det rundt');									-- ID = 6
insert into RecipeDB VALUES ('Mørk nøddetærte', 'Smelt Mørk Chokolade, tilsætter nøddestykker og flader det som en tærte');		-- ID = 7
insert into RecipeDB VALUES ('Jordbær flødebolle', 'Smelt Chokoladen, tilsæt jordbær, sprøjt fyld på bund og form flødebollen');-- ID = 8
insert into RecipeDB VALUES ('Træstamme', 'Form Marcipan til en stang, overtræk med chokolade og tilsæt nød på top');			-- ID = 9
insert into RecipeDB VALUES ('Chokoladetrøffel', 'Tag smeltet mørkchokolade og piskefløde, bland det sammen og stil på køl i et par timer, derefter form som en kugle og dyp i kakaopulver');-- ID = 10

-- RecipeLineItemDB (Quantity, ingredientsDB_ingredientsId, Foreign Key ("ID" For at vedhæfte flere ingredienser på en recipe))

-- Flødebolle Ingredienser
insert into RecipeLineItemDB VALUES (1, 1, 1); -- ID = 1
insert into RecipeLineItemDB VALUES (4, 2, 1); -- ID = 1
insert into RecipeLineItemDB VALUES (2, 3, 1); -- ID = 1
insert into RecipeLineItemDB VALUES (1, 4, 1); -- ID = 1
insert into RecipeLineItemDB VALUES (1, 5, 1); -- ID = 1
insert into RecipeLineItemDB VALUES (2, 6, 1); -- ID = 1

-- Chokolade Stykke Ingredienser
insert into RecipeLineItemDB VALUES (2, 1, 2);	-- ID = 2
insert into RecipeLineItemDB VALUES (2, 2, 2);	-- ID = 2
insert into RecipeLineItemDB VALUES (1, 3, 2);	-- ID = 2
insert into RecipeLineItemDB VALUES (1, 4, 2);	-- ID = 2
insert into RecipeLineItemDB VALUES (1, 5, 2);	-- ID = 2

-- Marcipan Stykke Ingredienser
insert into RecipeLineItemDB VALUES (7, 1, 3);	-- ID = 3
insert into RecipeLineItemDB VALUES (4, 3, 3);	-- ID = 3
insert into RecipeLineItemDB VALUES (2, 4, 3);	-- ID = 3
insert into RecipeLineItemDB VALUES (3, 6, 3);	-- ID = 3

-- Chokolade Æg Ingredienser
insert into RecipeLineItemDB VALUES (2, 7, 4);		-- ID = 4
insert into RecipeLineItemDB VALUES (1, 3, 4);		-- ID = 4
insert into RecipeLineItemDB VALUES (3, 3, 4);		-- ID = 4

-- Chokoladeknapper Ingredienser
insert into RecipeLineItemDB VALUES (1, 7, 5);		-- ID = 5
insert into RecipeLineItemDB VALUES (1, 16, 5);		-- ID = 5

-- Rom kapsel Ingredienser
insert into RecipeLineItemDB VALUES (1, 1, 6);		-- ID = 6
insert into RecipeLineItemDB VALUES (1, 13, 6);		-- ID = 6

-- Mørk nøddetærte Ingredienser
insert into RecipeLineItemDB VALUES (1, 1, 7);			-- ID = 7
insert into RecipeLineItemDB VALUES (1, 3, 7);			-- ID = 7
insert into RecipeLineItemDB VALUES (1, 5, 7);			-- ID = 7
insert into RecipeLineItemDB VALUES (2, 9, 7);			-- ID = 7

-- Jordbær flødebolle Ingredienser
insert into RecipeLineItemDB VALUES (1, 1, 8);						 -- ID = 8
insert into RecipeLineItemDB VALUES (4, 2, 8);						 -- ID = 8
insert into RecipeLineItemDB VALUES (2, 3, 8);						 -- ID = 8
insert into RecipeLineItemDB VALUES (1, 4, 8);						 -- ID = 8
insert into RecipeLineItemDB VALUES (1, 5, 8);						 -- ID = 8
insert into RecipeLineItemDB VALUES (2, 6, 8);						 -- ID = 8
insert into RecipeLineItemDB VALUES (3, 14, 8);						 -- ID = 8

-- Træstamme ingredienser
insert into RecipeLineItemDB VALUES (2, 1, 9);						-- ID = 9
insert into RecipeLineItemDB VALUES (1, 6, 9);						-- ID = 9
insert into RecipeLineItemDB VALUES (1, 9, 9);						-- ID = 9

-- Chokoladetrøffel ingredienser
insert into RecipeLineItemDB VALUES (1, 1, 10);						-- ID = 10
insert into RecipeLineItemDB VALUES (1, 2, 10);						-- ID = 10
insert into RecipeLineItemDB VALUES (1, 13, 10);					-- ID = 10

-- ProductDB (name, price, quantity, description, details, boxQuantity, recipeDB_recipeId)

insert into ProductDB VALUES ('Flødeboller', '50', '200', 'Frisklavede flødeboller', 'Detaljer her', '5', 1);			-- ID = 1
insert into ProductDB VALUES ('Chokolade Stykker', '60', '40', 'Frisklavede Chokolader', 'Detaljer her', '40', 2);		-- ID = 2
insert into ProductDB VALUES ('Marcipanstænger', '75', '210', 'Frisklavede marcipanstænger', 'Detaljer her', '10', 3);	-- ID = 3
insert into ProductDB VALUES ('Chokolade Æg', '125', '220', 'Frisklavede Chokolade æg', 'Detaljer her', '1', 4);		-- ID = 4
insert into ProductDB VALUES ('Chokoladeknapper', '35', '300', 'Frisklavede chokoladeknapper', 'Detaljer her', '20', 5); -- ID = 5
insert into ProductDB VALUES ('Rom kapsel', '50', '80', 'Frisklavede rom kapsler', 'Detaljer her', '15', 6); -- ID = 6
insert into ProductDB VALUES ('Mørk nøddetærte', '65', '40', 'Frisklavet mørk nøddetærte', 'Detaljer her', '5', 7); -- ID = 7
insert into ProductDB VALUES ('Jordbær flødebolle', '20', '100', 'Frisklavet jordbær flødebolle', 'Detaljer her', '8', 8); -- ID = 8
insert into ProductDB VALUES ('Træstamme', '15', '90', 'Frisklavet træstamme', 'Detaljer her', '6', 9); -- ID = 9
insert into ProductDB VALUES ('Chokoladetrøffel', '5', '30', 'Frisklavede chokoladetrøfler', 'Detaljer her', '25', 10); -- ID = 10


-- CompanyDB (name, adress, city, zipcode, cvr, phone, email)

insert into CompanyDB VALUES ('Kathine Andersen Chokolade A/S', 'Krogsvej 13, Vitten', 'Hinnerup', '8382', '30904478', '97640622' , 'info@kathrineandersen.com');		-- ID = 1


-- OrderDB (totalPrice, tax, date, status, discountPrice, CustomerDB_customerId, EmployeeDB_employeeId, CompanyDB_companyId)

insert into OrderDB VALUES ('500', '125', '11/03-2017', '1', '0', 1, (select EmployeeDB.id from EmployeeDB where id = '6'), 1);		-- ID = 1
insert into OrderDB VALUES ('1500', '375', '22/04-2018', '0', '100', 2, (select EmployeeDB.id from EmployeeDB where id = '7'), 1);	-- ID = 2
insert into OrderDB VALUES ('3000', '750', '22/04-2018', '1', '100', 3, (select EmployeeDB.id from EmployeeDB where id = '8'), 1);	-- ID = 3
insert into OrderDB VALUES ('1000', '250', '22/04-2018', '1', '100', 4, (select EmployeeDB.id from EmployeeDB where id = '9'), 1);	-- ID = 4
insert into OrderDB VALUES ('1000', '250', '24/05-2017', '1', '0', 4, (select EmployeeDB.id from EmployeeDB where id = '7'), 1);		-- ID = 5
insert into OrderDB VALUES ('3000', '750', '22/04-2018', '1', '200', 2, (select EmployeeDB.id from EmployeeDB where id = '8'), 1);	-- ID = 6
insert into OrderDB VALUES ('1000', '250', '17/08-2017', '1', '0', 1, (select EmployeeDB.id from EmployeeDB where id = '6'), 1);	-- ID = 7
insert into OrderDB VALUES ('2000', '500', '06/11-2017', '1', '0', 3, (select EmployeeDB.id from EmployeeDB where id = '9'), 1);	-- ID = 8
insert into OrderDB VALUES ('2000', '500', '09/04-2016', '0', '100', 2, (select EmployeeDB.id from EmployeeDB where id = '8'), 1);	-- ID = 9
insert into OrderDB VALUES ('1000', '250', '20/11-2017', '1', '1000', 2, (select EmployeeDB.id from EmployeeDB where id = '7'), 1);	-- ID = 10
insert into OrderDB VALUES ('1000', '250', '17/12-2017', '1', '0', 2, (select EmployeeDB.id from EmployeeDB where id = '6'), 1);	-- ID = 11
insert into OrderDB VALUES ('1000', '250', '02/12-2014', '0', '0', 4, (select EmployeeDB.id from EmployeeDB where id = '9'), 1);	-- ID = 12

-- SaleLineItemDB (quantity, price, ProductDB_productId, OrderDB_orderId)

insert into SaleLineItemDB VALUES ('10', '500', 1, 1);		-- ID = 1		-- Tilføj flere på OrderDB.id for at tilføje flere produkter til kunden
insert into SaleLineItemDB VALUES ('15', '1500', 2, 2);		-- ID = 2		-- Tilføj flere på OrderDB.id for at tilføje flere produkter til kunden
insert into SaleLineItemDB VALUES ('15', '1500', 3, 3);		-- ID = 3		-- Tilføj flere på OrderDB.id for at tilføje flere produkter til kunden
insert into SaleLineItemDB VALUES ('15', '1500', 4, 3);		-- ID = 4		-- Tilføj flere på OrderDB.id for at tilføje flere produkter til kunden
insert into SaleLineItemDB VALUES ('2', '1000', '5', '4'); -- ID = 5
insert into SaleLineItemDB VALUES ('5', '1000', '6', '5'); -- ID = 6
insert into SaleLineItemDB VALUES ('9', '1000', '7', '6'); -- ID = 7
insert into SaleLineItemDB VALUES ('11', '1000', '8', '6'); -- ID = 8
insert into SaleLineItemDB VALUES ('3', '1000', '9', '6'); -- ID = 9
insert into SaleLineItemDB VALUES ('44', '1000', '10', '7'); -- ID = 10
insert into SaleLineItemDB VALUES ('55', '1000', '1', '8'); -- ID = 11
insert into SaleLineItemDB VALUES ('1', '1000', '2', '8'); -- ID = 12
insert into SaleLineItemDB VALUES ('32', '1000', '3', '9'); -- ID = 13
insert into SaleLineItemDB VALUES ('12', '1000', '4', '9'); -- ID = 14
insert into SaleLineItemDB VALUES ('5', '1000', '5', '10'); -- ID = 15
insert into SaleLineItemDB VALUES ('15', '1000', '6', '11'); -- ID = 16
insert into SaleLineItemDB VALUES ('12', '1000', '7', '12'); -- ID = 17


