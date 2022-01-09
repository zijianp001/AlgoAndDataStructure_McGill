SELECT COUNT(*) AS numprojects
FROM
(SELECT DISTINCT pname
FROM documentauthors, document
WHERE documentauthors.employeeid='93401' AND documentauthors.documentid=document.documentid) AS foo
;
