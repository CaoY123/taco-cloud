DELETE FROM Taco_Order_Tacos;
DELETE FROM Taco_Ingredients;
DELETE FROM Taco;
DELETE FROM Taco_Order;
DELETE FROM USER;

DELETE FROM Ingredient;
INSERT INTO Ingredient (id, NAME, TYPE) 
                VALUES ('FLTO', 'Flour Tortilla', 'WRAP');
INSERT INTO Ingredient (id, NAME, TYPE) 
                VALUES ('COTO', 'Corn Tortilla', 'WRAP');
INSERT INTO Ingredient (id, NAME, TYPE) 
                VALUES ('GRBF', 'Ground Beef', 'PROTEIN');
INSERT INTO Ingredient (id, NAME, TYPE) 
                VALUES ('CARN', 'Carnitas', 'PROTEIN');
INSERT INTO Ingredient (id, NAME, TYPE) 
                VALUES ('TMTO', 'Diced Tomatoes', 'VEGGIES');
INSERT INTO Ingredient (id, NAME, TYPE) 
                VALUES ('LETC', 'Lettuce', 'VEGGIES');
INSERT INTO Ingredient (id, NAME, TYPE) 
                VALUES ('CHED', 'Cheddar', 'CHEESE');
INSERT INTO Ingredient (id, NAME, TYPE) 
                VALUES ('JACK', 'Monterrey Jack', 'CHEESE');
INSERT INTO Ingredient (id, NAME, TYPE) 
                VALUES ('SLSA', 'Salsa', 'SAUCE');
INSERT INTO Ingredient (id, NAME, TYPE) 
                VALUES ('SRCR', 'Sour Cream', 'SAUCE');
                
# 通过注册页面插入用户信息（为了验证密码加密）
# insert into user(id, username, `password`, `fullname`, `street`, `city`, `state`, `zip`, `phoneNumber`)



