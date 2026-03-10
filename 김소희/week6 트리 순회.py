from collections import defaultdict

N = int(input())
tree = defaultdict(list)

def preorder(node):
    if node == '.':
        return
    print(node, end="")
    preorder(tree[node][0])
    preorder(tree[node][1])

def inorder(node):
    if node == '.':
        return
    inorder(tree[node][0])
    print(node, end="")
    inorder(tree[node][1])

def postorder(node):
    if node == '.':
        return
    postorder(tree[node][0])
    postorder(tree[node][1])
    print(node, end="")

for _ in range(N):
    node, left, right = map(str, input().split())
    tree[node].append(left)
    tree[node].append(right)

preorder("A")
print()
inorder("A")
print()
postorder("A")
