/*
1)	��� ����(salary)�� ���� ���� �����?
 */
SELECT C.country_name "�����̸�", avg(salary) "��տ���"
FROM employees E, departments D, locations L, countries C
WHERE E.department_id = D.department_id
      AND D.location_id = L.location_id
      AND L.country_id = C.country_id
      AND (C.country_id, E.salary) in (SELECT country_id, salary
                                       FROM (SELECT C.country_id, avg(salary) ct_avg
                                             FROM employees E, departments D, locations L, countries C 
                                             WHERE E.department_id = D.department_id
                                                   AND D.location_id = L.location_id
                                                   AND L.country_id = C.country_id
                                             GROUP BY C.country_id) A , 
                                            (SELECT max(ct_avg) max_ct_avg
                                             FROM (SELECT C.country_id, avg(salary) ct_avg
                                                   FROM employees E, departments D, locations L, countries C 
                                                   WHERE E.department_id = D.department_id
                                                         AND D.location_id = L.location_id
                                                         AND L.country_id = C.country_id
                                                   GROUP BY C.country_id)) B
                                             WHERE A.ct_avg = B.max_ct_avg)
GROUP BY country_name;

/*
2)	��� ����(salary)�� ���� ���� ������?
*/

SELECT region_name "�����̸�", round(lo_avg,1) "��տ���"
FROM ( SELECT  R.region_name, avg(E.salary) lo_avg
        FROM employees E, departments D, locations L, countries C, regions R
        WHERE E.department_id = D.department_id 
              AND D.location_id = L.location_id
              AND L.country_id = C.country_id
              AND C.region_id = R.region_id
        GROUP BY R.region_name
ORDER BY lo_avg DESC )
WHERE ROWNUM <= 1;

/*
3)	���� ���� ������ �ִ� �μ��� � �μ��ΰ���?
*/

SELECT department_name �μ��̸�, co ������
FROM (  SELECT  D.department_name , count(E.employee_id) co
        FROM employees E, departments D
        WHERE E.department_id = D.department_id 
        GROUP BY D.department_name 
        ORDER BY co DESC )        
WHERE ROWNUM <= 1;