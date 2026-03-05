N = int(input())
cranes = list(map(int, input().split()))

M = int(input())
boxes = list(map(int, input().split()))

cranes.sort(reverse=True)
boxes.sort(reverse=True)

if boxes[0] > cranes[0]:
    print(-1)
    exit()

time = 0

while boxes:
    time += 1
    boxi = 0
    cranei = 0

    while cranei < N and boxi < len(boxes):
        if cranes[cranei] >= boxes[boxi]:
            boxes.pop(boxi)
            cranei += 1
        else:
            boxi += 1

print(time)
