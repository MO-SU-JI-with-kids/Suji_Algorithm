import heapq
from collections import defaultdict

N, M = map(int, input().split())
graph = defaultdict(list)

for _ in range(M):
    A, B, C = map(int, input().split())
    graph[A].append((B, C))
    graph[B].append((A, C))

A, B = map(int, input().split())

visited = [False]*(N+1)
pq = []

heapq.heappush(pq, (-float('inf'), A))

# 최대값을 꺼내고, 최소로 갱신한다.
while pq:
    cost, node = heapq.heappop(pq)

    if visited[node]:
        continue

    visited[node] = True

    if node == B:
        print(-cost)
        break

    for n, w in graph[node]:
        heapq.heappush(pq, (max(cost, -w), n))
	
