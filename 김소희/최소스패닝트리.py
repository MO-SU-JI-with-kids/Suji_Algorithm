from collections import defaultdict
import heapq

V, E = map(int, input().split())
graph = defaultdict(list)
for _ in range(E):
    a, b, cost = map(int, input().split())
    graph[a].append((cost, b))
    graph[b].append((cost, a))

# 최소 스패닝 트리는, 
# 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 
# 그 가중치의 합이 최소인 트리를 말한다.

visited = [False] * (V+1)
pq = [(0, 1)]
sum = 0

while pq:
    w, node = heapq.heappop(pq)
    if visited[node]:
        continue        
    visited[node] = True
    sum += w
    
    for cost, next in graph[node]:
        if not visited[next]:            
            heapq.heappush(pq, (cost, next))
        

print(sum)
