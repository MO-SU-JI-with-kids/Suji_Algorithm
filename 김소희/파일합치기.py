# 두 개의 파일을 합칠 때 필요한 비용(시간 등)이 두 파일 크기의 합이라고 가정할 때, 
# 최종적인 한 개의 파일을 완성하는데 필요한 비용의 최소비용을 계산하시오.
import heapq

T = int(input())
for _ in range(T):
    N = int(input())
    Files = list(map(int, input().split()))
    Files.sort()
    sum = 0
    heapq.heapify(Files)
    while len(Files) > 1:
        a = heapq.heappop(Files)
        b = heapq.heappop(Files)
        sum += a+b
        heapq.heappush(Files, a+b)
        
    print(sum)
