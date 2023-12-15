from itertools import product

def solution(word):
    answer = 0
    s = ['A', 'E', 'I', 'O', 'U']
    perm= []
    for i in range(1,6):
        perm += [''.join(p) for p in list(product(s, repeat=i))]
    perm.sort()    
    answer = perm.index(word)
    
    return answer+1