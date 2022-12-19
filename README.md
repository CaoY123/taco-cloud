# 工程简介

# 墨西哥煎饼玉米卷案例

## 各数据表信息说明：
1. Ingredient：保存配料信息
2. Taco：保存Taco设计的相关信息
3. Taco_Ingredients：Taco中的每行数据都对应一行或多行，将Taco和与之相关的配料映射到一起
4. Taco_Order：保存必要的订单信息
5. Taco_Orders_Tacos：Taco_Order中的每行数据都对应一行或多行，将订单和与之相关的Taco映射到一起
#### 注：SpringBoot在启动的时候将会自动执行根类路径下的名为schema.sql和data.sql的文件

## 第四章
#### 一个大坑：
1. 加上spring-boot-starter-security后，其默认开启防止跨域访问攻击，这里通过配置WebSecurityConfig类来解决这个问题，主要是
http.csrf().disable() 发生了作用
参考链接：https://stackoverflow.com/questions/64940922/there-was-an-unexpected-error-type-forbidden-status-403-forbiden
2. 使用默认的内嵌的h2数据库加载时位于resources的schema.sql和data.sql文件，要注意不能加#注释，
否则会出现启动时初始化数据库执行SQL错误
3. 直接按照书籍上的步骤和代码无法按照LDAP存储用户数据，
请参考链接：https://medium.com/@renquanbo7453/spring-security-with-ldap-based-user-store-6ac947c48f7c
