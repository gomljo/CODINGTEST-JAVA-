def possible(arr, n):
    result = []
    if n==0:
        return [[]]
    
    for i in range(len(arr)):
        e = arr[i]
        
        for rest in possible(arr[:i] + arr[i+1:], n-1):
            result.append([e] + rest)
    return result

def solution(k, dungeons):
    answer = -1
    
    idx = [i for i in range(len(dungeons))]
    
    pos_idx = possible(idx, len(dungeons))
    max_try = []
    
    for p_i in pos_idx:
        cost = k    
        cnt = 0

        for i in p_i:
            if dungeons[i][0] > cost:
                break
            else:
                cost -= dungeons[i][1]
                cnt += 1
        
        max_try.append(cnt)
    answer = max(max_try)
    return answer