N, M, Q = map(int, input().split())
tree = [[0] * (M + 2) for _ in range(N + 2)]

# 2차원 Fenwick Tree 업데이트
def update(x, y, diff):
    i = x
    while i <= N:
        j = y
        while j <= M:
            tree[i][j] += diff
            j += (j & -j)
        i += (i & -i)

# (1,1) ~ (x,y) 누적합
def query(x, y):
    result = 0
    i = x
    while i > 0:
        j = y
        while j > 0:
            result += tree[i][j]
            j -= (j & -j)
        i -= (i & -i)
    return result

for _ in range(Q):
    data = list(map(int, input().split()))
    
    if data[0] == 1:
        _, x1, y1, x2, y2, v = data
        
        # 직사각형 구간 업데이트 (4번)
        update(x1, y1, v)
        update(x1, y2 + 1, -v)
        update(x2 + 1, y1, -v)
        update(x2 + 1, y2 + 1, v)
        
    else:
        _, x, y = data
        print(query(x, y))
