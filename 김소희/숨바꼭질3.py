from collections import deque

N, K = map(int, input().split())
MAX = 200001

CostL = [float('inf')] * MAX
CostL[N] = 0

dq = deque([N])

while dq:
    x = dq.popleft()

    if x == K:
        break

    for nx, w in ((2*x, 0), (x-1, 1), (x+1, 1)):
        if 0 <= nx < MAX and CostL[nx] > CostL[x] + w:
            CostL[nx] = CostL[x] + w
            if w == 0:
                dq.appendleft(nx)
            else:
                dq.append(nx)

print(CostL[K])
