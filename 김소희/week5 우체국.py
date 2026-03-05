# i번째 마을은 X[i]에 위치해 있으며, A[i]명의 사람이 살고 있다.
N = int(input())

villages = []
total = 0

for _ in range(N):
    x, a = map(int, input().split())
    villages.append((x, a))
    total += a

villages.sort()

half = (total + 1) // 2

cur = 0
for x, a in villages:
    cur += a
    if cur >= half:
        print(x)
        break
