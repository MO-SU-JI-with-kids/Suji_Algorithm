import heapq
from collections import defaultdict

N, E = map(int, input().split())

graph = defaultdict(list)
for _ in range(E):
    a, b, cost = map(int, input().split())
    graph[a].append((cost, b))
    graph[b].append((cost, a))

v1, v2 = map(int, input().split())
# case1 : 1 -> v1 -> v2 -> N

# case1 - cost1 : 1 -> v1
cost1 = 0
if 1 != v1:    
    costL = [float('inf')] * (N+1)
    costL[1] = 0
    pq = [(0, 1)]
    while pq:
        cost, node = heapq.heappop(pq)
        currCost = costL[node]
        if currCost < cost:
            continue
        costL[node] = cost
        for w, next in graph[node]:
            newCost = cost + w
            if costL[next] > newCost:
                costL[next] = newCost
                heapq.heappush(pq, (costL[next], next))
    cost1 = costL[v1]

# case1 - cost2 : v1 -> v2
cost2 = 0
costL = [float('inf')] * (N+1)
costL[v1] = 0
pq = [(0, v1)]
while pq:
    cost, node = heapq.heappop(pq)
    currCost = costL[node]
    if currCost < cost:
        continue
    costL[node] = cost
    for w, next in graph[node]:
        newCost = cost + w
        if costL[next] > newCost:
            costL[next] = newCost
            heapq.heappush(pq, (costL[next], next))
cost2 = costL[v2]

# case1 - cost3 : v2 -> N
cost3 = 0
if v2 != N:
    costL = [float('inf')] * (N+1)
    costL[v2] = 0
    pq = [(0, v2)]
    while pq:
        cost, node = heapq.heappop(pq)
        currCost = costL[node]
        if currCost < cost:
            continue
        costL[node] = cost
        for w, next in graph[node]:
            newCost = cost + w
            if costL[next] > newCost:
                costL[next] = newCost
                heapq.heappush(pq, (costL[next], next))
    cost3 = costL[N]

case1 = cost1 + cost2 + cost3

# case2 : 1 -> v2 -> v1 -> N

# case2 - cost1 : 1 -> v2
cost1 = 0
costL = [float('inf')] * (N+1)
costL[1] = 0
pq = [(0, 1)]
while pq:
    cost, node = heapq.heappop(pq)
    currCost = costL[node]
    if currCost < cost:
        continue
    costL[node] = cost
    for w, next in graph[node]:
        newCost = cost + w
        if costL[next] > newCost:
            costL[next] = newCost
            heapq.heappush(pq, (costL[next], next))
cost1 = costL[v2]

# case2 - cost2 : v2 -> v1
cost2 = 0
costL = [float('inf')] * (N+1)
costL[v2] = 0
pq = [(0, v2)]
while pq:
    cost, node = heapq.heappop(pq)
    currCost = costL[node]
    if currCost < cost:
        continue
    costL[node] = cost
    for w, next in graph[node]:
        newCost = cost + w
        if costL[next] > newCost:
            costL[next] = newCost
            heapq.heappush(pq, (costL[next], next))
cost2 = costL[v1]

# case2 - cost3 : v1 -> N
cost3 = 0

costL = [float('inf')] * (N+1)
costL[v1] = 0
pq = [(0, v1)]
while pq:
    cost, node = heapq.heappop(pq)
    currCost = costL[node]
    if currCost < cost:
        continue
    costL[node] = cost
    for w, next in graph[node]:
        newCost = cost + w
        if costL[next] > newCost:
            costL[next] = newCost
            heapq.heappush(pq, (costL[next], next))
cost3 = costL[N]

case2 = cost1 + cost2 + cost3

result = min(case1, case2)
if result == float('inf'):
    print(-1)
else:
    print(result)



