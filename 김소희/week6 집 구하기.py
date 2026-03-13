# 맥세권 - 사각형은 맥도날드
# 맥도날드와 집 사이의 최단거리가 x이하인 집

# 스세권 - 별은 스타벅스
# 스타벅스와 집 사이의 최단거리가 y이하인 집

import heapq
from collections import defaultdict

tree = defaultdict(list)
V, E = map(int, input().split())
for _ in range(E):
    u, v, w = map(int, input().split())
    tree[u].append((w, v)) # cost, node
    tree[v].append((w, u))

# 맥도날드 수, 맥세권일 조건
M, x = map(int, input().split())
MacSet = set(map(int, input().split())) # 맥도날드 번호

# 스타벅스 수, 스세권일 조건
S, y = map(int, input().split())
StarSet = set(map(int, input().split())) # 스타벅스 번호

# 맥세권과 스세권을 만족하는 집 중 최단거리의 합이 최소인 집 중 최단거리 합!
# 만일 원하는 집이 존재하지 않으면 -1을 출력한다.

VSet = {i for i in range(1, V+1)}
HomeSet = VSet - MacSet - StarSet
HomeList = list(HomeSet)

INF = float('inf')

Mac_Star_dp = [[INF] * 2 for _ in range(V+1)]  # 0:Mac, 1:Star


def dijkstra(start_set, idx):
    pq = []

    for s in start_set:
        Mac_Star_dp[s][idx] = 0
        heapq.heappush(pq, (0, s))

    while pq:
        cost, node = heapq.heappop(pq)

        if Mac_Star_dp[node][idx] < cost:
            continue

        for w, n in tree[node]:
            new_cost = cost + w
            if Mac_Star_dp[n][idx] > new_cost:
                Mac_Star_dp[n][idx] = new_cost
                heapq.heappush(pq, (new_cost, n))


# 맥도날드 기준 다익스트라
dijkstra(MacSet, 0)

# 스타벅스 기준 다익스트라
dijkstra(StarSet, 1)

answer = INF

for h in HomeList:
    mac_dist = Mac_Star_dp[h][0]
    star_dist = Mac_Star_dp[h][1]

    if mac_dist <= x and star_dist <= y:
        answer = min(answer, mac_dist + star_dist)

print(answer if answer != INF else -1)
