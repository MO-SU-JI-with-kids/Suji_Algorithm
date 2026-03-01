import heapq

M, N = map(int, input().split())
MapL = []
for _ in range(N):
    MapL.append(list(input()))

pq = [(0, (0, 0))]
CostL = [[float('inf')] * M for _ in range(N)]
CostL[0][0] = 0

while pq:
    cost, (r, c) = heapq.heappop(pq)
    if CostL[r][c] < cost:
        continue

    for dr, dc in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
        nr, nc = r + dr, c + dc
        if 0 <= nr < N and 0 <= nc < M:
            newCost = CostL[r][c]
            if MapL[nr][nc] == '1':
                newCost += 1
            if CostL[nr][nc] > newCost:
                CostL[nr][nc] = newCost
                heapq.heappush(pq, (newCost, (nr, nc)))

print(CostL[N-1][M-1])

