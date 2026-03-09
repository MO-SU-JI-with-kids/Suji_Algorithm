N = int(input())

A, B, C, D = [], [], [], []

for _ in range(N):
    a, b, c, d = map(int, input().split())
    A.append(a)
    B.append(b)
    C.append(c)
    D.append(d)

AB = []
CD = []

for i in range(N):
    for j in range(N):
        AB.append(A[i] + B[j])
        CD.append(C[i] + D[j])

AB.sort()
CD.sort()

i = 0
j = len(CD) - 1

count = 0

while i < len(AB) and j >= 0:

    s = AB[i] + CD[j]

    if s == 0:
        a = AB[i]
        b = CD[j]

        cnt1 = 0
        cnt2 = 0

        while i < len(AB) and AB[i] == a:
            i += 1
            cnt1 += 1

        while j >= 0 and CD[j] == b:
            j -= 1
            cnt2 += 1

        count += cnt1 * cnt2

    elif s < 0:
        i += 1
    else:
        j -= 1

print(count)
