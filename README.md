# 工程简介

# 墨西哥煎饼玉米卷案例

## 各数据表信息说明：
1. Ingredient：保存配料信息
2. Taco：保存Taco设计的相关信息
3. Taco_Ingredients：Taco中的每行数据都对应一行或多行，将Taco和与之相关的配料映射到一起
4. Taco_Order：保存必要的订单信息
5. Taco_Orders_Tacos：Taco_Order中的每行数据都对应一行或多行，将订单和与之相关的Taco映射到一起
#### 注：SpringBoot在启动的时候将会自动执行根类路径下的名为schema.sql和data.sql的文件
