
N = int(input())
dp = [[-1] * (1<<N) for _ in range(N)]
INF = float('inf')

W = [[0] * N for _ in range(N)]
# N개 도시의 좌표
CoordinateL = []
for _ in range(N):
    x, y = map(int, input().split())
    CoordinateL.append((x, y))
    
for i in range(N):
    for j in range(i, N):
        cost = (CoordinateL[i][0] - CoordinateL[j][0])**2 + (CoordinateL[i][1] - CoordinateL[j][1])**2
        W[i][j] = cost ** 0.5
        W[j][i] = cost ** 0.5
        
def TSP(cur, visited):
    
    # 모두 방문했을 경우
    if visited == (1<<N)-1:        
        return W[cur][0] # 시초로 가는 비용 반환

    if dp[cur][visited] != -1:
        return dp[cur][visited]
    
    # 계산
    dp[cur][visited] = INF
    for next in range(N):
        if visited & (1<<next):
            continue
        cost = W[cur][next] + TSP(next, visited | (1<<next))
        dp[cur][visited] = min(dp[cur][visited], cost)

    return dp[cur][visited]

print(TSP(0, 1))
        
