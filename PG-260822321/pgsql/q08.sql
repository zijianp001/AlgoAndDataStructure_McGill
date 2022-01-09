SELECT DISTINCT pname
FROM documentauthors, document
WHERE documentauthors.employeeid='93401' AND documentauthors.documentid=document.documentid
ORDER BY pname
;
