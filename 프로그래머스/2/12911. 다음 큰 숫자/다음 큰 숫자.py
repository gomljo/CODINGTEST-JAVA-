def solution(n):
    answer = 0
    s = [s_ for s_ in bin(n)[2:] if s_=='1']
    cnt = 0
    idx = 1
    print(len(s))
    while True:
        n += idx
        cnt = len([s_ for s_ in bin(n)[2:] if s_=='1'])
        if cnt == len(s):
            answer = n
            break
    return answer