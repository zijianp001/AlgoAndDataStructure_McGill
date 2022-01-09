WITH pronameAndAuthor AS
(SELECT DISTINCT document.pname, documentauthors.employeeid
FROM document, documentauthors
WHERE document.documentid=documentauthors.documentid)

SELECT pname, employeeid
FROM pronameAndAuthor
EXCEPT(SELECT DISTINCT project.pname, developer.employeeid
FROM project, developer, devassignments
WHERE developer.employeeid=devassignments.employeeid AND devassignments.pname = project.pname)
ORDER BY pname, employeeid
;
