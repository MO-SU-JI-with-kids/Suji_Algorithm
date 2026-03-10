from collections import defaultdict, deque

graph = defaultdict(list)

N = int(input())
for _ in range(N):
    line = list(map(int, input().split()))
    
    node = line[0]
    for i in range(1, len(line)-1, 2):
        if line[i] == -1:
            break
        graph[node].append((line[i], line[i+1]))

def bfs(start):
    visited = [False]*(N+1)
    Q = deque([(start, 0)])
    visited[start] = True
    
    far_node = start
    max_dist = 0
    
    while Q:
        node, dist = Q.popleft()
        
        if dist > max_dist:
            max_dist = dist
            far_node = node
        
        for nxt, cost in graph[node]:
            if not visited[nxt]:
                visited[nxt] = True
                Q.append((nxt, dist+cost))
    
    return far_node, max_dist


# 1번 BFS
far, _ = bfs(1)

# 2번 BFS
_, answer = bfs(far)

print(answer)
