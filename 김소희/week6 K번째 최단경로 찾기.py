import heapq
from collections import defaultdict, deque

N, M, K = map(int, input().split())
graph = defaultdict(list)

# 도로의 수
for _ in range(M):
    A, B, time = map(int, input().split())
    graph[A].append((time, B))

# 1번 도시에서 각 도시로 가는 K번째 최단경로
dp = [[] for _ in range(N+1)] 
dp[1].append(0)

# 최단경로에 같은 정점이 여러 번 포함되어도 된다.
start = 1
pq = [(0, start)]
while pq:
    cost, node = heapq.heappop(pq)
    for time, next in graph[node]:
        new_cost = cost + time
        if len(dp[next]) < K:
            dp[next].append(new_cost)
            heapq.heappush(pq, (new_cost, next))
        else:
            if max(dp[next]) > new_cost:
                dp[next].remove(max(dp[next]))
                dp[next].append(new_cost)
                heapq.heappush(pq, (new_cost, next))

for n in range(1, N+1):
    dp[n].sort()
    # print(dp[n])
    if len(dp[n]) < K:
        print(-1)
    else:
        print(dp[n][K-1])
