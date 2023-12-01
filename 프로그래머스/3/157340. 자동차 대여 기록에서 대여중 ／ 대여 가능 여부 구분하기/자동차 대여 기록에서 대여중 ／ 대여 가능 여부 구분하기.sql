-- 코드를 입력하세요
SELECT CAR_ID,
case 
when count(case when DATE(START_DATE) <= '2022-10-16' and '2022-10-16' <= DATE(END_DATE) then 1 end) > 0 then '대여중'
else '대여 가능'
end
as AVAILABILITY
from CAR_RENTAL_COMPANY_RENTAL_HISTORY 
group by CAR_ID
order by CAR_ID desc;

# select * from CAR_RENTAL_COMPANY_RENTAL_HISTORY where car_id = 30;