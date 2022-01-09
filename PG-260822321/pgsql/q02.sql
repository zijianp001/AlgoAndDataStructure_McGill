SELECT DISTINCT developer.employeeid, developer.ename
FROM developer, documentauthors 
WHERE developer.employeeid=documentauthors.employeeid AND documentauthors.documentid='22'
ORDER BY developer.employeeid
;
