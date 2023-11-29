def convert_over_ten(num, n):
    ten_c = {10:'A', 11:'B', 12:'C', 13:'D', 14:'E', 15:'F'}
    if n > 10 and num >= 10:
        return ten_c[num]
    else:
        return num
    
def convert(n, num):
    c = []
    
    if num==0:
        c.append(num)
        return c
    
    while num > 0:
    
        rem = num % n
        num = num // n
        rem = str(convert_over_ten(rem, n))
        
        if rem.isnumeric():
            if int(rem) > 9: 
                rem = list(str(rem))
                c.extend(rem)
            else:
                c.insert(0, rem)
        else:
            c.insert(0,rem)
                
    return c

def solution(n, t, m, p):
    answer = ''
    num = 0
    seq = []
    idxs = [i*m + p-1 for i in range(t+1)]
    con = convert(16, 10)
    
    while len(seq) < idxs[-1]+1:
        con = convert(n, num)
        seq.extend(con)
        num+=1
    
    for i in range(len(idxs)-1):
        answer+=str(seq[idxs[i]])
    return answer