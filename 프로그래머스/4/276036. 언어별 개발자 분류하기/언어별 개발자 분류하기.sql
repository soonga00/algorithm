SELECT 
  CASE
    WHEN d.SKILL_CODE & python.CODE > 0
         AND EXISTS (
           SELECT 1
           FROM SKILLCODES f
           WHERE f.CATEGORY = 'Front End'
             AND d.SKILL_CODE & f.CODE > 0
         ) THEN 'A'
    WHEN d.SKILL_CODE & csharp.CODE > 0 THEN 'B'
    WHEN EXISTS (
           SELECT 1
           FROM SKILLCODES f
           WHERE f.CATEGORY = 'Front End'
             AND d.SKILL_CODE & f.CODE > 0
         ) THEN 'C'
    ELSE NULL
  END AS GRADE,
  d.ID,
  d.EMAIL
FROM DEVELOPERS d
LEFT JOIN SKILLCODES python ON python.NAME = 'Python'
LEFT JOIN SKILLCODES csharp ON csharp.NAME = 'C#'
WHERE 
  CASE
    WHEN d.SKILL_CODE & python.CODE > 0
         AND EXISTS (
           SELECT 1 FROM SKILLCODES f WHERE f.CATEGORY = 'Front End' AND d.SKILL_CODE & f.CODE > 0
         ) THEN 1
    WHEN d.SKILL_CODE & csharp.CODE > 0 THEN 1
    WHEN EXISTS (
           SELECT 1 FROM SKILLCODES f WHERE f.CATEGORY = 'Front End' AND d.SKILL_CODE & f.CODE > 0
         ) THEN 1
    ELSE 0
  END = 1
ORDER BY GRADE ASC, ID ASC;
