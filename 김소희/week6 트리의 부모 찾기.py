from collections import defaultdict, deque

N = int(input())

graph = defaultdict(list)
for _ in range(N-1):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)


Q = deque([1])
visitedL = [False] * (N+1)
parentL = [-1] * (N+1)
while Q:
    node = Q.popleft()    
    visitedL[node] = True

    for n in graph[node]:
        if not visitedL[n]:
            parentL[n] = node
            Q.append(n)

for i in range(2, N+1):
    print(parentL[i])
