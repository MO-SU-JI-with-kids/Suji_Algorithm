from collections import deque

N, M = map(int, input().split())
Map2L = [list(map(int, input().split())) for _ in range(N)]
 
empties = []
virus = []

for r in range(N):
    for c in range(M):
        if Map2L[r][c] == 0:
            empties.append((r, c))
        elif Map2L[r][c] == 2:
            virus.append((r, c))

answer = 0

def bfs():
    visited = [[False]*M for _ in range(N)]
    Q = deque()

    for r, c in virus:
        Q.append((r, c))
        visited[r][c] = True

    cnt = 0
    while Q:
        r, c = Q.popleft()
        for dr, dc in [(-1,0),(1,0),(0,-1),(0,1)]:
            nr, nc = r+dr, c+dc
            if 0 <= nr < N and 0 <= nc < M:
                if not visited[nr][nc] and Map2L[nr][nc] == 0:
                    visited[nr][nc] = True
                    cnt += 1
                    Q.append((nr, nc))
    return cnt


def dfs(idx, wall):
    global answer
    if wall == 3:
        infected = bfs()
        answer = max(answer, len(empties) - 3 - infected)
        return

    for i in range(idx, len(empties)):
        r, c = empties[i]
        Map2L[r][c] = 1
        dfs(i+1, wall+1)
        Map2L[r][c] = 0


dfs(0, 0)
print(answer)
