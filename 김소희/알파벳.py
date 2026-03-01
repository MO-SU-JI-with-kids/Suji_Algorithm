R, C = map(int, input().split())
MapL = []
for _ in range(R):
    MapL.append(list(input()))

# 말은 인접한 네 칸 중의 한 칸으로 이동할 수 있다.
visitedL = [[False] * C for _ in range(R)]
Alpha = [False] * 26

# init
visitedL[0][0] = True
FirstNumI = ord(MapL[0][0]) - ord('A')
Alpha[FirstNumI] = True

MaxCount = 0
def dfs(r, c, count):        
    global MaxCount
    for dr, dc in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
        nr, nc = r + dr, c + dc
        if 0 <= nr < R and 0 <= nc < C and not visitedL[nr][nc]:
            numberI = ord(MapL[nr][nc]) - ord('A')
            if not Alpha[numberI]:
                visitedL[nr][nc] = True
                Alpha[numberI] = True
                MaxCount = max(MaxCount, count + 1)
                dfs(nr, nc, count + 1)
                visitedL[nr][nc] = False
                Alpha[numberI] = False
    return

dfs(0, 0, 1)
print(MaxCount)
