import heapq

K = int(input())
C, R = map(int, input().split())

Map = [list(map(int, input().split())) for _ in range(R)]

horseD = [(-2,-1), (-1,-2), (2,-1), (1,-2), (-2,1), (-1,2), (2,1), (1,2)]
monkeyD = [(-1,0), (1,0), (0,-1), (0,1)]

INF = float('inf')
costL = [[[INF]*(K+1) for _ in range(C)] for _ in range(R)]
costL[0][0][0] = 0

pq = [(0, 0, 0, 0)]  # cost, r, c, horse_used

while pq:
    cost, r, c, horse = heapq.heappop(pq)

    if cost > costL[r][c][horse]:
        continue

    if r == R-1 and c == C-1:
        print(cost)
        exit()

    if horse < K:
        for dr, dc in horseD:
            nr, nc = r+dr, c+dc
            if 0<=nr<R and 0<=nc<C and Map[nr][nc]==0:
                if costL[nr][nc][horse+1] > cost+1:
                    costL[nr][nc][horse+1] = cost+1
                    heapq.heappush(pq, (cost+1, nr, nc, horse+1))

    for dr, dc in monkeyD:
        nr, nc = r+dr, c+dc
        if 0<=nr<R and 0<=nc<C and Map[nr][nc]==0:
            if costL[nr][nc][horse] > cost+1:
                costL[nr][nc][horse] = cost+1
                heapq.heappush(pq, (cost+1, nr, nc, horse))

ans = min(costL[R-1][C-1])
print(ans if ans != INF else -1)
