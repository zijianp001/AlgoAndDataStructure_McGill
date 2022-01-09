SELECT pname, budget
FROM project
WHERE budget>(SELECT AVG(budget) 
	FROM project
	WHERE project.ptype='internal')
ORDER BY budget DESC, pname
;
