import sys
sys.setrecursionlimit(10**5)

pre = list(map(int, sys.stdin.read().split()))
n = len(pre)

def postorder(start, end):
    if start > end:
        return

    root = pre[start]
    idx = end + 1

    for i in range(start+1, end+1):
        if pre[i] > root:
            idx = i
            break

    postorder(start+1, idx-1)
    postorder(idx, end)
    print(root)

postorder(0, n-1)
