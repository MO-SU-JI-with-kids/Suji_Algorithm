import heapq
from collections import defaultdict

N, M = map(int, input().split())

graph = defaultdict(list)
for _ in range(M):
    a, b, cost = map(int, input().split())
    graph[a].append((cost, b))
    graph[b].append((cost, a))

pq = [(0, 1)]
costL = [float('inf')] * (N+1)
costL[1] = 0

while pq:
    cost, node = heapq.heappop(pq)
    if costL[node] < cost:
        continue

    costL[node] = cost
    for w, next in graph[node]:
        newCost = costL[node] + w
        if costL[next] > newCost:
            costL[next] = newCost
            heapq.heappush(pq, (costL[next], next))
            
print(costL[N])
