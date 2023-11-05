

def solution(n):
    answer = 0
    
    if n < 3:
        return n
    else:
        d = [0] * (n+1)
        d[0] = 1
        d[1] = 2
        d[2] = 0
        
        for i in range(3, n+1):
            d[2] = d[1] + d[0]
            d[0] = d[1]
            d[1] = d[2]
        return d[2] % 1234567