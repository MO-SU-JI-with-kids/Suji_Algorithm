N = int(input())
AList = list(map(int, input().split()))
opList = list(map(int, input().split()))

minCost = float('inf')
maxCost = -float('inf')

def dfs(index, cost):
    global minCost, maxCost
    if index == len(AList):
        minCost = min(minCost, cost)
        maxCost = max(maxCost, cost)
        return

    for i, op in enumerate(opList):
        if op > 0:
            opList[i] -= 1
            if i == 0:                
                dfs(index+1, cost + AList[index])
            elif i == 1:
                dfs(index+1, cost - AList[index])
            elif i == 2:
                dfs(index+1, cost * AList[index])
            elif i == 3:
                dfs(index+1, int(cost / AList[index]))
            opList[i] += 1

dfs(1, AList[0])
print(maxCost)
print(minCost)
