# MST를 만듦
# 간선을 하나 삭제

import heapq
from collections import defaultdict

N, M = map(int, input().split())

graph = defaultdict(list)
for _ in range(M):
    a, b, cost = map(int, input().split())
    graph[a].append((cost, b))
    graph[b].append((cost, a))

visited = [False] * (N+1)
pq = [(0, 1)]
edge = []
sum = 0
max_edge = 0
while pq:
    cost, node = heapq.heappop(pq)
    if visited[node]:
        continue
    visited[node] = True
    sum += cost
    max_edge = max(max_edge, cost)
    for w, next in graph[node]:
        if not visited[next]:
            heapq.heappush(pq, (w, next))

print(sum - max_edge)
    
