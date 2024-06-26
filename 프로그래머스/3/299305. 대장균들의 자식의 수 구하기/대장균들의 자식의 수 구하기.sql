-- 코드를 작성해주세요
select outer_ed.id AS ID, if(ed.child_count is not null, ed.child_count, 0) as CHILD_COUNT
from ecoli_data as outer_ed 
left outer join (select inner_ed.parent_id, count(inner_ed.parent_id) as child_count 
from ecoli_data as inner_ed
group by inner_ed.parent_id) as ed 
on outer_ed.id=ed.parent_id;
