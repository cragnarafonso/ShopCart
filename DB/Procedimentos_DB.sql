-- Procedimentos

-- Focar a database necessária
use mydb;

-- ************************************ TABELA STM_SITE

-- Selecionar dados de uma determinada database (stm_site)
select * from stm_site;

-- Introduzir dados numa tabela
-- Notas: Type: EC -> Ecommerce | Status: ACT -> ACTIVE; DIS -> DISABLE; SUS -> SUSPENDED
insert into stm_site (site_ds, site_tp, site_st, site_jd) values ('Tec Shop', 'EC', 'ACT', '{"Information" : "Technology Web-Site"}');
insert into stm_site (site_ds, site_tp, site_st, site_jd) values ('Pet Shop', 'EC', 'ACT', '{"Information" : "Pet Web-Site"}');
insert into stm_site (site_ds, site_tp, site_st, site_jd) values ('Clothes Shop', 'EC', 'ACT', '{"Information" : "Clothes Web-Site"}');

-- ************************************ TABELA STM_PRODUCT
-- Selecionar dados de uma determinada database (stm_product)
select * from stm_product;

-- Introduzir dados numa tabela
-- Status: ACT -> ACTIVE; DIS -> DISABLE; SUS -> SUSPENDED
insert into stm_product (product_ds,product_st,product_jd,product_image_cd) values 
('Product1','ACT','{"Information" : "The right handed gaming mouse that´s favored by Esports players, features ergonomic form, hyperesponse buttons"}', NULL), 
('Product2','ACT','{"Information" : "The right handed gaming mouse that´s favored by Esports players, features ergonomic form, hyperesponse buttons"}', NULL), 
('Product3','ACT','{"Information" : "The right handed gaming mouse that´s favored by Esports players, features ergonomic form, hyperesponse buttons"}', NULL), 
('Product4','ACT','{"Information" : "The right handed gaming mouse that´s favored by Esports players, features ergonomic form, hyperesponse buttons"}', NULL), 
('Bedsure Orthopedic Dog1','ACT','{"Information" : "Removable cover with zip closure for easy cleaning. The inner foam has a TPU cover, which provides protection from dog pee and excrement. "}','/home/christophe/Documents/AgileFactor_Desafio/Image/bed.png'), 
('Bedsure Orthopedic Dog2','ACT','{"Information" : "Removable cover with zip closure for easy cleaning. The inner foam has a TPU cover, which provides protection from dog pee and excrement. "}','/home/christophe/Documents/AgileFactor_Desafio/Image/bed.png'), 
('Bedsure Orthopedic Dog3','ACT','{"Information" : "Removable cover with zip closure for easy cleaning. The inner foam has a TPU cover, which provides protection from dog pee and excrement. "}','/home/christophe/Documents/AgileFactor_Desafio/Image/bed.png'), 
('Bedsure Orthopedic Dog4','ACT','{"Information" : "Removable cover with zip closure for easy cleaning. The inner foam has a TPU cover, which provides protection from dog pee and excrement. "}','/home/christophe/Documents/AgileFactor_Desafio/Image/bed.png'), 
('Golf Pant1','ACT','{"Information" : "Fits with slight ease through hip and thigh with straight leg. Sits at the waist"}','/home/christophe/Documents/AgileFactor_Desafio/Image/pants.png'),
('Golf Pant2','ACT','{"Information" : "Fits with slight ease through hip and thigh with straight leg. Sits at the waist"}','/home/christophe/Documents/AgileFactor_Desafio/Image/pants.png'),
('Golf Pant3','ACT','{"Information" : "Fits with slight ease through hip and thigh with straight leg. Sits at the waist"}','/home/christophe/Documents/AgileFactor_Desafio/Image/pants.png'),
('Golf Pant4','ACT','{"Information" : "Fits with slight ease through hip and thigh with straight leg. Sits at the waist"}','/home/christophe/Documents/AgileFactor_Desafio/Image/pants.png'),
('Golf Pant5','ACT','{"Information" : "Fits with slight ease through hip and thigh with straight leg. Sits at the waist"}','/home/christophe/Documents/AgileFactor_Desafio/Image/pants.png'),
('Golf Pant6','ACT','{"Information" : "Fits with slight ease through hip and thigh with straight leg. Sits at the waist"}','/home/christophe/Documents/AgileFactor_Desafio/Image/pants.png'),
('Golf Pant7','ACT','{"Information" : "Fits with slight ease through hip and thigh with straight leg. Sits at the waist"}','/home/christophe/Documents/AgileFactor_Desafio/Image/pants.png');

-- ************************************ TABELA STM_PRODUCT
-- Selecionar dados de uma determinada database (stm_product_code)
select * from stm_product_code;
-- Selecionar dados de uma determinada database (stm_product_code)
-- TYPE: PER -> peripherals; TOY -> toys; PAT -> panths

insert into stm_product_code (product_id,product_code_cd,product_code_tp,product_code_jd) values 
(1, 'TECMOUSE', 'PER', null),
(2, 'TECKEYBOARD', 'PER', null),
(3, 'TECHEADSETS_1', 'PER', null),
(4, 'TECHEADSETS_2', 'PER', null),
(5, 'PETBED_1', 'TOY', null),
(6, 'PETBED_2', 'TOY', null),
(7, 'PETBED_3', 'TOY', null),
(8, 'PETBED_4', 'TOY', null),
(9, 'CLOUTHSPANTS_1', 'PAT', null),
(10, 'CLOUTHSPANTS_2', 'PAT', null),
(11, 'CLOUTHSPANTS_3', 'PAT', null),
(12, 'CLOUTHSPANTS_4', 'PAT', null),
(13, 'CLOUTHSPANTS_5', 'PAT', null),
(14, 'CLOUTHSPANTS_6', 'PAT', null),
(15, 'CLOUTHSPANTS_7', 'PAT', null);

-- ************************************ TABELA STM_SITE_PRODUCT
-- Selecionar dados de uma determinada database (stm_site_product)
select * from stm_site_product where site_id = 1;
-- Selecionar dados de uma determinada database (stm_site_product)
insert into stm_site_product (site_id, product_id) values 
(1,1), (1,2), (1,3), (1,4), 
(2,5), (2,6), (2,7), (2,8),
(3,9), (3,10), (3,11), (3,12), (3,13), (3,14), (3,15);

-- ************************************ TABELA STM_SITE_PRODUCT_CODE
-- Selecionar dados de uma determinada database (stm_site_product_code)
select * from stm_site_product_code;
-- Selecionar dados de uma determinada database (stm_site_product_code)
insert into stm_site_product_code (site_id,product_id,product_code_cd,product_code_tp,product_code_jd) values 
(1, 1, 'TECMOUSE', 'PER', null),
(1, 2, 'TECKEYBOARD', 'PER', null),
(1, 3, 'TECHEADSETS_1', 'PER', null),
(1, 4, 'TECHEADSETS_2', 'PER', null),
(2, 5, 'PETBED_1', 'TOY', null),
(2, 6, 'PETBED_2', 'TOY', null),
(2, 7, 'PETBED_3', 'TOY', null),
(2, 8, 'PETBED_4', 'TOY', null),
(3, 9, 'CLOUTHSPANTS_1', 'PAT', null),
(3, 10, 'CLOUTHSPANTS_2', 'PAT', null),
(3, 11, 'CLOUTHSPANTS_3', 'PAT', null),
(3, 12, 'CLOUTHSPANTS_4', 'PAT', null),
(3, 13, 'CLOUTHSPANTS_5', 'PAT', null),
(3, 14, 'CLOUTHSPANTS_6', 'PAT', null),
(3, 15, 'CLOUTHSPANTS_7', 'PAT', null);

-- ************************************ TABELA STM_SITE_PRODUCT_PRICE
-- Selecionar dados de uma determinada database (stm_site_product_price)
select * from stm_site_product_price;

SELECT stm_site_product_price.*, stm_product.product_ds, stm_product.product_jd FROM stm_site_product_price 
INNER JOIN stm_product
ON stm_site_product_price.product_id = stm_product.product_id AND stm_site_product_price.site_id=1;

-- Selecionar dados de uma determinada database (stm_site_product_price)
insert into stm_site_product_price(site_id,product_id,currency_cd,start_dt,price_vl,price_st) values
(1, 1, "EUR", curdate(), 109.22, "ACT"),
(1, 2, "EUR", curdate(), 200.00, "ACT"),
(1, 3, "EUR", curdate(), 55.32, "ACT"),
(1, 4, "EUR", curdate(), 20.78, "ACT"),
(2, 5, "EUR", curdate(), 58.54, "ACT"),
(2, 6, "EUR", curdate(), 250.25, "ACT"),
(2, 7, "EUR", curdate(), 65.75, "ACT"),
(2, 8, "EUR", curdate(), 78.65, "ACT"),
(3, 9, "EUR", curdate(), 5.72, "ACT"),
(3, 10, "EUR", curdate(), 2.32, "ACT"),
(3, 11, "EUR", curdate(), 11.54, "ACT"),
(3, 12, "EUR", curdate(), 501.65, "ACT"),
(3, 13, "EUR", curdate(), 36.85, "ACT"),
(3, 14, "EUR", curdate(), 1.75, "ACT"),
(3, 15, "EUR", curdate(), 0.34, "ACT");

-- ************************************ TABELA STM_SITE_CLIENT
-- Selecionar dados de uma determinada database (stm_site_client)
select * from stm_site_client;
-- Selecionar dados de uma determinada database (stm_site_client)
insert into stm_site_client(site_id, client_ds) values 
(1, 'cliente1@gmail.com'),
(2, 'cliente2@gmail.com'),
(3, 'cliente3@gmail.com'),
(3, 'cliente4@gmail.com'),
(2, 'cliente5@gmail.com'),
(1, 'cliente6@gmail.com'),
(1, 'cliente7@gmail.com'),
(3, 'cliente8@gmail.com'),
(2, 'cliente9@gmail.com');

-- ************************************ TABELA STM_SITE_SESSION
-- Selecionar dados de uma determinada database ()
select * from stm_site_session ;
-- Selecionar dados de uma determinada database ()
insert into stm_site_session(site_id,session_tk,client_id,created_dt,updated_dt) values 
(1, 'sessiontoken1', 1, curdate(), now()),
(2, 'sessiontoken2', 2, curdate(), now()),
(3, 'sessiontoken3', 3, curdate(), now()),
(3, 'sessiontoken4', 4, curdate(), now()),
(2, 'sessiontoken5', 5, curdate(), now()),
(1, 'sessiontoken6', 6, curdate(), now()),
(1, 'sessiontoken7', 7, curdate(), now()),
(3, 'sessiontoken8', 8, curdate(), now()),
(2, 'sessiontoken9', 9, curdate(), now());






