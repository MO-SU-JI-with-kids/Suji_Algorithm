N, S = map(int, input().split())
List = list(map(int, input().split()))
start = 0
end = 0
sum = List[0]
length = float('inf')
while start <= end:
    if sum >= S:
        length = min(length, end-start+1)
        sum -= List[start]
        start += 1
    else:
        end += 1
        if end == N:
            break
        sum += List[end]

if length == float('inf'):
    print(0)
else:
    print(length)
