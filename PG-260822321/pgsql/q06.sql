SELECT ename, employeeid
FROM developer
WHERE employeeid IN
(SELECT DISTINCT employeeid
FROM devassignments, project
WHERE devassignments.pname=project.pname AND project.ptype='internal'
EXCEPT (SELECT DISTINCT employeeid
FROM devassignments, project
WHERE devassignments.pname=project.pname AND project.ptype='external'))
ORDER BY employeeid
;
