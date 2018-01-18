/*
1) EMPLOYEE ���̺��� �̸�(Last Name)�� ��hae���� �����ϰ� �ִ� ������ ���� �μ����� �ٹ��ϰ� �ִ� ����� 
EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DEPARTMENT_ID �� ����϶�. 
*/

SELECT employee_id, first_name, last_name, department_id
FROM employees
WHERE department_id in (SELECT department_id
                        FROM employees
                        WHERE last_name like '%hae%'
                        GROUP BY department_id)
ORDER BY employee_id desc;

/*
2) �� ����(city)�� ���� ������ ���� �ް� �ִ� ����� ���� �̸�(City), First Name, Last Name, �޿��� ��ȸ�϶�. 
�޿� ������ �������� �����Ͻÿ�. (1-2.sql)
*/  

SELECT L.city, E.first_name, E.last_name, E.salary
FROM  employees E, departments D, locations L
WHERE E.department_id = D.department_id
      AND D.location_id = L.location_id
      AND (city, salary) in (SELECT L.city, max(salary)
                             FROM employees E, departments D, locations L 
                             WHERE E.department_id = D.department_id
                                   AND D.location_id = L.location_id 
                             GROUP BY city)
ORDER BY salary asc;
