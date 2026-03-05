 # N개의 회의를 모두 진행할 수 있는 최소 회의실 개수를 구하라
import heapq

N = int(input())

TimeL = []
for _ in range(N):
    start, end = map(int, input().split())
    TimeL.append((start, end))

TimeL.sort()

pq = []

for start, end in TimeL:
    
    if pq and pq[0] <= start:
        heapq.heappop(pq)

    heapq.heappush(pq, end)

print(len(pq))
