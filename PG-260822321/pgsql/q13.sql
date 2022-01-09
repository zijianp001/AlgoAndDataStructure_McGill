SELECT pname, COUNT(foo.documentid) AS numdocs
FROM(SELECT project.pname, documentid, project.ptype
     FROM project LEFT OUTER JOIN document
     ON project.pname=document.pname) AS foo
WHERE foo.ptype='internal'
GROUP BY foo.pname
ORDER BY numdocs DESC, foo.pname ASC
;
