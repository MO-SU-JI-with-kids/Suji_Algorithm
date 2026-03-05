import heapq

N, K = map(int, input().split())

JewelryL = []
for _ in range(N):
    M, V = map(int, input().split())
    JewelryL.append((M, V))
JewelryL.sort()

BagL = []
for _ in range(K):
    C = int(input())
    BagL.append(C)
BagL.sort()

pq = []
answer = 0
idx = 0

for bag in BagL:
    while idx < N and JewelryL[idx][0] <= bag:
        heapq.heappush(pq, -JewelryL[idx][1])
        idx += 1
    
    if pq:
        answer += -heapq.heappop(pq)

print(answer)
