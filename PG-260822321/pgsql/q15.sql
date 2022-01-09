SELECT foo.pname, foo.pstartdate, COUNT(foo.employeeid) AS numdevs
FROM
(SELECT project.pname, pstartdate, employeeid
FROM project LEFT OUTER JOIN devassignments
ON project.pname=devassignments.pname 
WHERE pstartdate=(SELECT MAX(pstartdate)FROM project)) AS foo
GROUP BY foo.pname, pstartdate
ORDER BY foo.pname
;
