# 建立配料表
CREATE TABLE IF NOT EXISTS Ingredient (
  `id` VARCHAR(4) PRIMARY KEY NOT NULL,
  `name` VARCHAR(25) NOT NULL,
  `type` VARCHAR(10) NOT NULL
);

# 创建Taco表
CREATE TABLE IF NOT EXISTS Taco (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `createdAt` TIMESTAMP NOT NULL
);

# Taco_Ingredients表：即为Taco与Ingredient的一对多关系的表
CREATE TABLE IF NOT EXISTS Taco_Ingredients (
  `taco` BIGINT NOT NULL,
  `ingredient` VARCHAR(4) NOT NULL
);

# 添加外键，建立Taco 和 Ingredient的联系
ALTER TABLE Taco_Ingredients
    ADD FOREIGN KEY (`taco`) REFERENCES Taco(`id`);
ALTER TABLE Taco_Ingredients
    ADD FOREIGN KEY (`ingredient`) REFERENCES Ingredient(`id`);

# 添加Taco_Order表（与Taco的关系是一对多）
CREATE TABLE IF NOT EXISTS Taco_Order (
	`id` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
	`street` VARCHAR(50) NOT NULL,
	`city` VARCHAR(50) NOT NULL,
	`state` VARCHAR(100) NOT NULL,
	`zip` VARCHAR(100) NOT NULL,
	`ccNumber` VARCHAR(16) NOT NULL,
	`ccExpiration` VARCHAR(5) NOT NULL,
	`ccCVV` VARCHAR(3) NOT NULL,
	`placedAt` TIMESTAMP NOT NULL
);

# 创建表Taco_Order_Tacos
CREATE TABLE IF NOT EXISTS Taco_Order_Tacos (
	`tacoOrder` BIGINT NOT NULL,
	`taco` BIGINT NOT NULL
);

# 添加外键
ALTER TABLE Taco_Order_Tacos
    ADD FOREIGN KEY (`tacoOrder`) REFERENCES Taco_Order(`id`);
ALTER TABLE Taco_Order_Tacos
    ADD FOREIGN KEY (`taco`) REFERENCES Taco(`id`);

# 创建用户表
CREATE TABLE IF NOT EXISTS USER (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(256) NOT NULL,
    `fullname` VARCHAR(100),
    `street` VARCHAR(80),
    `city` VARCHAR(30),
    `state` VARCHAR(30),
    `zip` VARCHAR(50),
    `phone` VARCHAR(20) NOT NULL
);

