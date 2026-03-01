import heapq
from collections import defaultdict

# 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오.

graph = defaultdict(list)

V, E = map(int, input().split())
K = int(input())

for _ in range(E):
    a, b, cost = map(int, input().split())
    graph[a].append((cost, b))

costL = [float('inf')] * (V+1)
costL[K] = 0
pq = [(0, K)]

while pq:
    # A에서 n으로 가는 비용
    cost, n = heapq.heappop(pq)    
    
    # 현재 기록이 더 좋다면 넘어가기
    if costL[n] < cost:
        continue

    # 현재가 가장 작은 기록이라면 갱신, 다음 노드 탐색
    costL[n] = cost
    for w, next in graph[n]:
        newCost = cost + w
        if newCost < costL[next]:
            costL[next] = newCost
            heapq.heappush(pq, (costL[next], next))

for v in range(1, V+1):
    if not costL[v] == float('inf'):
        print(costL[v])
    else:
        print("INF")
    
    
