SELECT ename, employeeid
FROM developer
WHERE employeeid IN
(SELECT employeeid 
FROM devassignments 
WHERE devassignments.pname='Kodiak'
UNION ALL
SELECT employeeid
FROM documentauthors, document
WHERE documentauthors.documentid=document.documentid AND document.pname='Kodiak')
ORDER BY employeeid
;

