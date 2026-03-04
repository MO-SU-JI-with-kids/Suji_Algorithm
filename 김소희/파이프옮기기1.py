N = int(input())
MapL = [list(map(int, input().split())) for _ in range(N)]
dp = [[[0]*3 for _ in range(N)] for _ in range(N)]
dp[0][1][0] = 1   # (0,1)에 가로로 놓여 있음

for r in range(N):
    for c in range(N):
        if MapL[r][c] == 1:
            continue

        # 가로
        if dp[r][c][0] > 0:
            # 가로
            if c+1 < N and MapL[r][c+1] == 0:
                dp[r][c+1][0] += dp[r][c][0]

            # 대각선
            if r+1 < N and c+1 < N and \
               MapL[r][c+1] == 0 and MapL[r+1][c] == 0 and MapL[r+1][c+1] == 0:
                dp[r+1][c+1][2] += dp[r][c][0]

        # 세로
        if dp[r][c][1] > 0:
            # 세로
            if r+1 < N and MapL[r+1][c] == 0:
                dp[r+1][c][1] += dp[r][c][1]

            # 대각선
            if r+1 < N and c+1 < N and \
               MapL[r][c+1] == 0 and MapL[r+1][c] == 0 and MapL[r+1][c+1] == 0:
                dp[r+1][c+1][2] += dp[r][c][1]

        # 대각선
        if dp[r][c][2] > 0:
            # 가로
            if c+1 < N and MapL[r][c+1] == 0:
                dp[r][c+1][0] += dp[r][c][2]

            # 세로
            if r+1 < N and MapL[r+1][c] == 0:
                dp[r+1][c][1] += dp[r][c][2]

            # 대각선
            if r+1 < N and c+1 < N and \
               MapL[r][c+1] == 0 and MapL[r+1][c] == 0 and MapL[r+1][c+1] == 0:
                dp[r+1][c+1][2] += dp[r][c][2]

print(sum(dp[N-1][N-1]))
