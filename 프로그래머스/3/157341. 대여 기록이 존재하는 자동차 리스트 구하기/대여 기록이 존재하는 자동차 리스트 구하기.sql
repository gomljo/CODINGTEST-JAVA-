-- 코드를 입력하세요
SELECT distinct(crcrh.CAR_ID)
from CAR_RENTAL_COMPANY_RENTAL_HISTORY as crcrh
join CAR_RENTAL_COMPANY_CAR as crcc on crcrh.CAR_ID = crcc.CAR_ID
where crcc.CAR_TYPE='세단' and crcrh.START_DATE >= '2022-10-01'
order by crcrh.CAR_ID desc;