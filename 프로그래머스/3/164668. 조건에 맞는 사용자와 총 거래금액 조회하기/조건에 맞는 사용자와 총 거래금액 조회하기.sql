-- 코드를 입력하세요
SELECT USER_ID, NICKNAME, sum(ugb.PRICE) as TOTAL_SALES
from USED_GOODS_USER as ugu
join USED_GOODS_BOARD as ugb on ugu.USER_ID = ugb.WRITER_ID
where ugb.status = 'DONE'
group by ugu.USER_ID
having TOTAL_SALES >=700000
order by TOTAL_SALES