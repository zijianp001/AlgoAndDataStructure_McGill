SELECT pname, pstartdate
FROM project
WHERE pstartdate=(SELECT MIN(pstartdate)
FROM project)
ORDER BY pname
;
