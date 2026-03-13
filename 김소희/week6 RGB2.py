N = int(input())
RGB = []
for _ in range(N):
    RGB.append(list(map(int, input().split())))

INF = float('inf')
result = INF

# 처음 집 색상을 3가지 돌려 DP 3번 진행
for start in range(3):
    dp = [[INF] * 3 for _ in range(N)]
    dp[0][start] = RGB[0][start]

    for i in range(1, N):
        dp[i][0] = min(dp[i-1][1], dp[i-1][2]) + RGB[i][0]
        dp[i][1] = min(dp[i-1][0], dp[i-1][2]) + RGB[i][1]
        dp[i][2] = min(dp[i-1][1], dp[i-1][0]) + RGB[i][2]

        for last in range(3):
            if start != last:
                result = min(result, dp[N-1][last])

print(result)
