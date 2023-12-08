-- 코드를 입력하세요

SELECT ANIMAL_INS.NAME, ANIMAL_INS.DATETIME 
FROM ANIMAL_INS 
WHERE ANIMAL_INS.ANIMAL_ID NOT IN 
(SELECT 
 DISTINCT ANIMAL_OUTS.ANIMAL_ID 
 FROM ANIMAL_OUTS)
 ORDER BY ANIMAL_INS.DATETIME ASC LIMIT 3;