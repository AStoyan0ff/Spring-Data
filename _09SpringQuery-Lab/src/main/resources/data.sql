-- Departments
INSERT INTO departments (id, name, location) VALUES (1, 'Engineering', 'Sofia');
INSERT INTO departments (id, name, location) VALUES (2, 'Marketing', 'Plovdiv');
INSERT INTO departments (id, name, location) VALUES (3, 'Finance', 'Sofia');

-- Projects
INSERT INTO projects (id, name, budget) VALUES (1, 'Project Alpha', 50000.00);
INSERT INTO projects (id, name, budget) VALUES (2, 'Project Beta', 30000.00);
INSERT INTO projects (id, name, budget) VALUES (3, 'Project Gamma', 75000.00);
INSERT INTO projects (id, name, budget) VALUES (4, 'Project Delta', 20000.00);

-- Employees
INSERT INTO employees (id, first_name, last_name, email, salary, hire_date, department_id) VALUES (1, 'Petar', 'Ivanov', 'petar.ivanov@company.bg', 3200.00, '2021-03-15', 1);
INSERT INTO employees (id, first_name, last_name, email, salary, hire_date, department_id) VALUES (2, 'Maria', 'Petrova', 'maria.petrova@company.bg', 3800.00, '2020-06-01', 1);
INSERT INTO employees (id, first_name, last_name, email, salary, hire_date, department_id) VALUES (3, 'Georgi', 'Ivanov', 'georgi.ivanov@company.bg', 2900.00, '2022-01-10', 2);
INSERT INTO employees (id, first_name, last_name, email, salary, hire_date, department_id) VALUES (4, 'Elena', 'Dimitrova', 'elena.d@company.bg', 4200.00, '2019-09-20', 1);
INSERT INTO employees (id, first_name, last_name, email, salary, hire_date, department_id) VALUES (5, 'Ivan', 'Todorov', 'ivan.todorov@company.bg', 2600.00, '2023-02-14', 3);
INSERT INTO employees (id, first_name, last_name, email, salary, hire_date, department_id) VALUES (6, 'Sofia', 'Koleva', 'sofia.koleva@company.bg', 3500.00, '2021-07-05', 2);
INSERT INTO employees (id, first_name, last_name, email, salary, hire_date, department_id) VALUES (7, 'Dimitar', 'Georgiev', 'dimitar.g@company.bg', 4500.00, '2018-11-30', 1);
INSERT INTO employees (id, first_name, last_name, email, salary, hire_date, department_id) VALUES (8, 'Anna', 'Stoyanova', 'anna.s@company.bg', 2800.00, '2023-05-22', 3);
INSERT INTO employees (id, first_name, last_name, email, salary, hire_date, department_id) VALUES (9, 'Nikolay', 'Marinov', 'nikolay.m@company.bg', 3100.00, '2022-08-18', 2);
INSERT INTO employees (id, first_name, last_name, email, salary, hire_date, department_id) VALUES (10, 'Viktoria', 'Hristova', 'viktoria.h@company.bg', 3900.00, '2020-12-01', 1);

-- Employee <-> Project (ManyToMany join table)
-- Note: Ivan (5) and Anna (8) intentionally have NO projects (for Task 11)
INSERT INTO employees_projects (employee_id, project_id) VALUES (1, 1);
INSERT INTO employees_projects (employee_id, project_id) VALUES (1, 2);
INSERT INTO employees_projects (employee_id, project_id) VALUES (2, 1);
INSERT INTO employees_projects (employee_id, project_id) VALUES (2, 3);
INSERT INTO employees_projects (employee_id, project_id) VALUES (3, 2);
INSERT INTO employees_projects (employee_id, project_id) VALUES (4, 1);
INSERT INTO employees_projects (employee_id, project_id) VALUES (4, 3);
INSERT INTO employees_projects (employee_id, project_id) VALUES (4, 4);
INSERT INTO employees_projects (employee_id, project_id) VALUES (6, 4);
INSERT INTO employees_projects (employee_id, project_id) VALUES (7, 3);
INSERT INTO employees_projects (employee_id, project_id) VALUES (9, 4);
INSERT INTO employees_projects (employee_id, project_id) VALUES (10, 1);
INSERT INTO employees_projects (employee_id, project_id) VALUES (10, 3);
