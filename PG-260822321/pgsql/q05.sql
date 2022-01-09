SELECT pname, pstartdate
FROM project
WHERE pstartdate>'2020-04-30' AND pstartdate<'2020-09-01' 
ORDER BY pstartdate, pname
;
