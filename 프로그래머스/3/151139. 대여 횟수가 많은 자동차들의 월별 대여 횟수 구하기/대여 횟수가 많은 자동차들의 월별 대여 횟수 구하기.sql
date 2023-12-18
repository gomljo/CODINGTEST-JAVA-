-- 코드를 입력하세요
SELECT MONTH(c.START_DATE) as MONTH, c.CAR_ID, count(*) as RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY c
where 8 <= MONTH(c.START_DATE) and MONTH(c.START_DATE) <= 10 
    and c.CAR_ID in 
    (   select c.CAR_ID
        from CAR_RENTAL_COMPANY_RENTAL_HISTORY c
        where 8 <= MONTH(c.START_DATE) and MONTH(c.START_DATE) <= 10
        group by c.CAR_ID
        having count(*) >= 5)
group by c.CAR_ID, MONTH(c.START_DATE)
having count(*) > 0
order by MONTH asc, c.CAR_ID desc;


# select MONTH(c.START_DATE) as MONTH, c.CAR_ID, sum(count(*) as RECORDS)
# from CAR_RENTAL_COMPANY_RENTAL_HISTORY c
# where 8 <= MONTH(c.START_DATE) and MONTH(c.END_DATE) <= 10
# group by c.CAR_ID, MONTH(c.START_DATE)
# having sum(count(*)) >=5;
# select
# from (
#         select MONTH(c.START_DATE) as MONTH, c.CAR_ID, count(*) as total
#         from CAR_RENTAL_COMPANY_RENTAL_HISTORY c
#         where 8 <= MONTH(c.START_DATE) and MONTH(c.END_DATE) <= 10
#     )
# group by c.CAR_ID
# having sum(count(*)) >=5;

# select c.CAR_ID
#         from CAR_RENTAL_COMPANY_RENTAL_HISTORY c
#         where 8 <= MONTH(c.START_DATE) and MONTH(c.END_DATE) <= 10
#         group by c.CAR_ID
#         having count(*) >= 5
 # select *
 # from CAR_RENTAL_COMPANY_RENTAL_HISTORY
 # where CAR_ID = 27