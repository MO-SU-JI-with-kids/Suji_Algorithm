import heapq
from collections import defaultdict

graph = defaultdict(list)

N = int(input())
M = int(input())

for _ in range(M):
    a, b, cost = map(int, input().split())
    graph[a].append((cost, b))

A, B = map(int, input().split())

costL = [float('inf')] * (N+1)
costL[A] = 0
pq = [(0, A)]

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

print(costL[B])
