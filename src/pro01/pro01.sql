/*
1) EMPLOYEE 테이블에서 이름(Last Name)에 “hae”를 포함하고 있는 사원들과 같은 부서에서 근무하고 있는 사원의 
EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DEPARTMENT_ID 를 출력하라. 
*/

SELECT employee_id, first_name, last_name, department_id
FROM employees
WHERE department_id in (SELECT department_id
                        FROM employees
                        WHERE last_name like '%hae%'
                        GROUP BY department_id)
ORDER BY employee_id desc;

/*
2) 각 도시(city)별 가장 연봉을 많이 받고 있는 사원의 도시 이름(City), First Name, Last Name, 급여를 조회하라. 
급여 순으로 오름차순 정렬하시오. (1-2.sql)
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
