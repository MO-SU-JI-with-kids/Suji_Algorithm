import heapq

count = 0
while True:
    N = int(input())
    count += 1
    if N == 0:
        break

    caveL = []
    for _ in range(N):
        caveL.append(list(map(int, input().split())))

    costL = [[float('inf')] * N for _ in range(N)]   
    costL[0][0] = caveL[0][0]
    pq = [(costL[0][0], (0, 0))]
    
    while pq:
        cost, (r, c) = heapq.heappop(pq)
        
        if costL[r][c] < cost:
            continue

        costL[r][c] = cost
        for dr, dc in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
            nr, nc = r + dr, c + dc
            if 0 <= nr < N and 0 <= nc < N:
                w = caveL[nr][nc]
                newCost = costL[r][c] + w
                if costL[nr][nc] > newCost:
                    costL[nr][nc] = newCost
                    heapq.heappush(pq, (newCost, (nr, nc)))

    print("Problem " + str(count) + ": " + str(costL[N-1][N-1]))
