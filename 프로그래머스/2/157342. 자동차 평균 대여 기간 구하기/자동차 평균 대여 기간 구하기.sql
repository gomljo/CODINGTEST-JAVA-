-- 코드를 입력하세요
select calc.CAR_ID, calc.avg_diff as AVERAGE_DURATION 
from (SELECT history_id, car_id, round(avg(DATEDIFF(end_date , start_date)+1),1) as avg_diff
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by car_id) as calc
where calc.avg_diff >= 7.0
order by AVERAGE_DURATION desc, CAR_ID desc; 