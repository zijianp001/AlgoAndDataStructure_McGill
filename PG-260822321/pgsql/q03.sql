SELECT developer.ename, developer.employeeid
FROM developer, devassignments
WHERE developer.employeeid=devassignments.employeeid AND devassignments.pname='Kodiak' AND developer.employeeid NOT IN (SELECT DISTINCT employeeid
                                                       FROM documentauthors, document
WHERE documentauthors.documentid=document.documentid AND document.pname='Kodiak')
ORDER BY developer.employeeid
;
