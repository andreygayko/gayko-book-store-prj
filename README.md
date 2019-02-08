Online market project 

Environment: 
1. Java 8 
2. Spring 5.0.8.RELEASE 
3. Hibernate 5.3.5.Final 
4. Tomcat 9.0.11 
5. Git
6. Apache Maven 3.5.4 
7. MySQL 8.0.12

Description:
Online shopping is a form of electronic commerce which allows consumers to directly buy goods or services from a seller over the Internet using a web browser. Consumers find a product of interest by visiting the website of the retailer directly or by searching among alternative vendors using a shopping search engine, which displays the same product's availability and pricing at different e-retailers.

Roles and functionality:
1. For all users: Login, Registration, Logout. 
2. Security user: Users: change role/password, disable, delete. 
3. Sale user: 
a)News: create, update, delete, delete comments. 
b)Items: create, copy, remove (soft delete), upload with help of xml.
c)Orders: show orders, change status(NEW, REVIEWING, IN_PROGRESS, DELIVERED). 
4. Customer user: 
a)News:  show, create comment for news. 
b)Orders: add item to bucket, create order, show orders. 
c)Profile: change information except email, change password.
5. API user:  Items: create, update, remove (soft delete), delete if not exists in any order. 
