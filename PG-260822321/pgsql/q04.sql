SELECT DISTINCT devassignments.pname, devassignments.asgndate, project.ptype
FROM devassignments, project
WHERE devassignments.employeeid='82102' AND devassignments.pname=project.pname
ORDER BY devassignments.pname
;
