N = int(input())
NumberL = list(map(int, input().split()))
NumberL.sort()

L = len(NumberL)

# 하나를 기준에 두고
best = float('inf')
answer = []
for i, num1 in enumerate(NumberL[:L-2]):
    
    left = i+1
    right = N-1

    while left < right:
        num2 = NumberL[left]
        num3 = NumberL[right]

        sum = num1 + num2 + num3
        if abs(sum) < best:
            best = abs(sum)
            answer = [num1, num2, num3]

        if sum > 0:
            right -= 1
        else:
            left += 1

print(*answer)
