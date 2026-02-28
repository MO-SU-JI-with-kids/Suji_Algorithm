from collections import defaultdict
 
N = int(input())
result = 0
    
Queen2L = [[False] * N for _ in range(N)]

def dfs(r):    
    global result
    global Queen2L
    
    if r == N:
        result += 1
        return

    for c in range(N):
        node = Queen2L[r][c]
        can = True
        # 세로
        for nr in range(r-1, -1, -1):
            if Queen2L[nr][c]:
                can = False
                break
        # 대각선
        if can:
            for i in range(1, r+1):
                nr = r - i
                nc = c + i      
                if 0 <= nc < N and Queen2L[nr][nc]:
                    can = False
                    break

                nc = c - i 
                if 0 <= nc < N and Queen2L[nr][nc]:
                    can = False
                    break
                
        # ㄱㄴ
        if can:
            Queen2L[r][c] = True
            dfs(r+1)
            Queen2L[r][c] = False
        
dfs(0)
print(result)
        
