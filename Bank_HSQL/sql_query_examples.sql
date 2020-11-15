CREATE TABLE employee(
    
    employee_ID INT PRIMARY KEY,
    first_name VARCHAR(10),
    last_name VARCHAR(10),
    birthday DATE,
    sex VARCHAR(1),
    salary INT,
    supervisor_ID INT,
    branch_ID INT

);

SELECT 
    *
FROM
    information_schema.tables;

CREATE TABLE branch (
  branch_ID INT PRIMARY KEY,
  branch_name VARCHAR(40),
  manager_ID INT,
  manager_start_date DATE,
  FOREIGN KEY(manager_ID) REFERENCES employee(employee_ID) ON DELETE SET NULL  
);


ALTER TABLE employee
ADD FOREIGN KEY(branch_ID)
REFERENCES branch(branch_ID)
ON DELETE SET NULL;

ALTER TABLE employee
ADD FOREIGN KEY(supervisor_ID)
REFERENCES employee(employee_ID)
ON DELETE SET NULL;

CREATE TABLE client (
  client_ID INT PRIMARY KEY,
  client_name VARCHAR(40),
  branch_id INT,
  FOREIGN KEY(branch_ID) REFERENCES branch(branch_ID) ON DELETE SET NULL
);

CREATE TABLE works_with(

employee_ID INT ,
client_ID INT,
total_sales INT,
PRIMARY KEY(employee_ID,client_ID),
FOREIGN KEY(employee_ID) REFERENCES employee(employee_ID) ON DELETE CASCADE,
FOREIGN KEY(client_ID) REFERENCES client(client_ID) ON DELETE CASCADE
)


CREATE TABLE branch_supplier(
branch_ID INT,
supplier_name VARCHAR(20) ,
supply_type VARCHAR(20),
PRIMARY KEY(branch_ID,supplier_name),
FOREIGN KEY(branch_ID) REFERENCES branch(BRANCH_ID) ON DELETE CASCADE

)


INSERT INTO employee VALUES(100, 'David', 'Wallace', '1967-11-17', 'M', 250000, NULL, NULL);

INSERT INTO branch VALUES(1, 'Corporate', 100, '2006-02-09');

-----------------------------------UPDATE an instance's fields---------------
UPDATE employee
SET branch_ID = 1
WHERE employee_ID = 100;

------------------------------------INSERT a new instance-----------------------

INSERT INTO employee VALUES(101, 'Jan', 'Levinson', '1961-05-11', 'F', 110000, 100, 1);



-- Scranton
INSERT INTO employee VALUES(102, 'Michael', 'Scott', '1964-03-15', 'M', 75000, 100, NULL);

INSERT INTO branch VALUES(2, 'Scranton', 102, '1992-04-06');

UPDATE employee
SET branch_ID = 2
WHERE employee_ID = 102;

INSERT INTO employee VALUES(103, 'Angela', 'Martin', '1971-06-25', 'F', 63000, 102, 2);
INSERT INTO employee VALUES(104, 'Kelly', 'Kapoor', '1980-02-05', 'F', 55000, 102, 2);
INSERT INTO employee VALUES(105, 'Stanley', 'Hudson', '1958-02-19', 'M', 69000, 102, 2);


-- Stamford
INSERT INTO employee VALUES(106, 'Josh', 'Porter', '1969-09-05', 'M', 78000, 100, NULL);

INSERT INTO branch VALUES(3, 'Stamford', 106, '1998-02-13');

UPDATE employee
SET branch_ID = 3
WHERE employee_ID = 106;

INSERT INTO employee VALUES(107, 'Andy', 'Bernard', '1973-07-22', 'M', 65000, 106, 3);
INSERT INTO employee VALUES(108, 'Jim', 'Halpert', '1978-10-01', 'M', 71000, 106, 3);


-- BRANCH SUPPLIER
INSERT INTO branch_supplier VALUES(2, 'Hammer Mill', 'Paper');
INSERT INTO branch_supplier VALUES(2, 'Uni-ball', 'Writing Utensils');
INSERT INTO branch_supplier VALUES(3, 'Patriot Paper', 'Paper');
INSERT INTO branch_supplier VALUES(2, 'J.T. Forms & Labels', 'Custom Forms');
INSERT INTO branch_supplier VALUES(3, 'Uni-ball', 'Writing Utensils');
INSERT INTO branch_supplier VALUES(3, 'Hammer Mill', 'Paper');
INSERT INTO branch_supplier VALUES(3, 'Stamford Lables', 'Custom Forms');

-- CLIENT
INSERT INTO client VALUES(400, 'Dunmore Highschool', 2);
INSERT INTO client VALUES(401, 'Lackawana Country', 2);
INSERT INTO client VALUES(402, 'FedEx', 3);
INSERT INTO client VALUES(403, 'John Daly Law, LLC', 3);
INSERT INTO client VALUES(404, 'Scranton Whitepages', 2);
INSERT INTO client VALUES(405, 'Times Newspaper', 3);
INSERT INTO client VALUES(406, 'FedEx', 2);

-- WORKS_WITH
INSERT INTO works_with VALUES(105, 400, 55000);
INSERT INTO works_with VALUES(102, 401, 267000);
INSERT INTO works_with VALUES(108, 402, 22500);
INSERT INTO works_with VALUES(107, 403, 5000);
INSERT INTO works_with VALUES(108, 403, 12000);
INSERT INTO works_with VALUES(105, 404, 33000);
INSERT INTO works_with VALUES(107, 405, 26000);
INSERT INTO works_with VALUES(102, 406, 15000);
INSERT INTO works_with VALUES(105, 406, 130000);


DESCRIBE TABLE employee;
DESCRIBE TABLE branch;
DESCRIBE TABLE CLIENT;
DESCRIBE TABLE works_with;
DESCRIBE TABLE branch_supplier;

SELECT * FROM  employee;
SELECT * FROM branch;
SELECT * FROM CLIENT;
SELECT * FROM works_with;
SELECT * FROM branch_supplier;

SELECT employee_ID AS ID 
FROM employee
ORDER BY branch_ID,salary,last_name,first_name
LIMIT  5;

SELECT DISTINCT sex 
fROM employee;


------------------------------------------------------------------------------------------------
--------------------------------------------functions-------------------------------------

SELECT COUNT(employee_ID) FROM employee;
SELECT  COUNT(DISTINCt supervisor_ID) FROM employee;

SELECT COUNT(sex),sex
FROM employee
GROUP BY sex;


SELECT AVG(salary)
FROM employee
WHERE sex='M';

SELECT SUM(salary)
FROM employee;


SELECT SUM(total_sales),employee_ID
FROM works_with
GROUP BY employee_ID;
--returns the total sales of one employee

SELECT * 
FROM DATABASE company;
SHOW TABLES;

SELECT *
FROM CLIENT 
WHERE client_name LIKE '%LLC';

--select all employees born in octomber
SELECT *
FROM employee
WHERE birthday LIKE '____-10%';



-----------------------------------------------------------------------------------------------------
---------------------------------------------UNION----------------------------------------------------
-- Find a list of employee and branch names
SELECT employee.first_name AS Employee_Branch_Names
FROM employee
UNION
SELECT branch.branch_name
FROM branch;



--------
-----------------------------------------------------------------------------------------------
------------------------------------------------JOINS-----------------------------------

-- Add the extra branch
INSERT INTO branch VALUES(4, "Buffalo", NULL, NULL);

SELECT employee.employee_ID AS 'EMPLOYEE', employee.first_name AS 'Numele prostului', branch.branch_name
FROM employee
JOIN branch    -- LEFT JOIN, RIGHT JOIN
ON employee.employee_ID = branch.manager_ID;


