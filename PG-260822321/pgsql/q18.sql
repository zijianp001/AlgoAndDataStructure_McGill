WITH numberOfDeveloper AS
(SELECT project.pname, COUNT(project.pname) AS ndev
	FROM project, devassignments
	WHERE project.pname=devassignments.pname
	GROUP BY project.pname)
, foo As
(SELECT DISTINCT project.pname, budget/ndev AS devcost
FROM project, numberOfDeveloper
WHERE project.pname=numberOfDeveloper.pname)

SELECT pname, devcost
FROM foo
WHERE devcost=(SELECT MAX(devcost) FROM foo)
ORDER BY devcost DESC, pname
;

