def parsing(s):
    s = s.strip('"').replace('{', '').replace('},', '|').replace('}}','|')
    max_leng = 0
    temp = []
    subsets = []
    
    s =s.split('|')
    for s_ in s:
        s_ = s_.split(',')
        s_ = [s__ for s__ in s_ if s__.isnumeric()]
        subsets.append(s_)
        if max_leng < len(s_):
            max_leng = len(s_)
    return subsets[:-1], max_leng

def solution(s):
    answer = []
    
    subset, cardinality = parsing(s)
    subset.sort(key=len)
    for i in range(cardinality):
        if len(answer) == 0:
            answer.append(subset[i][0])
            
        else:
            e = list(set(subset[i]) - set(answer))
            
            answer.append(e[0])
        
    answer = [int(s) for s in answer]
    return answer