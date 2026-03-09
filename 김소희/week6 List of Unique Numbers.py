N = int(input())
arr = list(map(int, input().split()))

visited = [False] * (100001)

L = 0
count = 0

for R in range(N):
    while visited[arr[R]]:
        visited[arr[L]] = False
        L += 1
    
    visited[arr[R]] = True
    count += (R - L + 1)

print(count)
