from datetime import datetime
import math
def solution(fees, records):
    answer = []
    t = {}
    records = sorted(records, key= lambda x:x.split(' ')[1])
    
    for i in range(len(records)):
        time, num, state = records[i].split(' ')
        t[num] = t.get(num, []) + [[datetime.strptime(time,"%H:%M"), state]]
    
    for key in t:
        duration = 0
        if len(t[key]) % 2 != 0:
            time_1 = datetime.strptime('23:59', "%H:%M")
            time_2 = t[key][-1][0]
            dura = time_1 - time_2
            duration += dura.seconds // 60
            for i in range(0, len(t[key])-1, 2):
                time_1 = t[key][i+1][0]
                time_2 = t[key][i][0]
                dura = time_1 - time_2
                duration += dura.seconds // 60
        else:
            for i in range(0, len(t[key]), 2):
                time_1 = t[key][i+1][0]
                time_2 = t[key][i][0]
                dura = time_1 - time_2
                duration += dura.seconds // 60
        if duration <= fees[0]:
            answer.append(fees[1])
        else:
            
            answer.append(fees[1] + math.ceil((duration - fees[0]) / fees[2] ) * fees[3])
    
    
    return answer