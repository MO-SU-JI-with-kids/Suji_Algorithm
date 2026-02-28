from collections import defaultdict

N = int(input())
map2L = []
for _ in range(N):
    map2L.append(list(input()))

visited2L = [[False]*N for _ in range(N)]
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

count = 0
def dfs(r, c):
    global count
    visited2L[r][c] = True    
    for i in range(4):
        nr, nc = r + dr[i], c + dc[i]        
        if 0 <= nr < N and 0 <= nc < N and not visited2L[nr][nc] and map2L[nr][nc] == "1":
            dfs(nr, nc)
            count += 1
            
village = 0
countL = []
for r in range(N):
    for c in range(N):
        if not visited2L[r][c] and map2L[r][c] == "1":
            count = 1
            dfs(r, c)
            village += 1
            countL.append(count)

print(village)
countL.sort()
for c in countL:
    print(c)
