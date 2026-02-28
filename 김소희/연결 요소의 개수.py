from collections import defaultdict

N, M = map(int, input().split())
graph = defaultdict(list)
for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

visitedL = [False] * (N+1)
def dfs(node):
    visitedL[node] = True
    for n in graph[node]:
        if not visitedL[n]:            
            dfs(n)

result = 0
for node in range(1, N+1):
    if not visitedL[node]:
        result += 1
        dfs(node)

print(result)
