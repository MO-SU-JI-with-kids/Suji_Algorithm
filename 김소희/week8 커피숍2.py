N, Q = map(int, input().split())
arr = list(map(int, input().split()))

tree = [0] * (4 * N)

# 1. 트리 만들기
def build(node, start, end):
    if start == end:
        tree[node] = arr[start]
        return tree[node]

    mid = (start + end) // 2
    left_sum = build(node * 2, start, mid)
    right_sum = build(node * 2 + 1, mid + 1, end)
    tree[node] = left_sum + right_sum
    return tree[node]

# 2. 구간 합 구하기
def query(node, start, end, left, right):
    # 현재 구간이 아예 범위 밖
    if right < start or end < left:
        return 0

    # 현재 구간이 완전히 포함됨
    if left <= start and end <= right:
        return tree[node]

    mid = (start + end) // 2
    left_sum = query(node * 2, start, mid, left, right)
    right_sum = query(node * 2 + 1, mid + 1, end, left, right)
    return left_sum + right_sum

# 3. 특정 인덱스 값 변경하기
def update(node, start, end, idx, value):
    # 바꾸려는 인덱스가 현재 구간 밖
    if idx < start or idx > end:
        return

    # 리프 노드면 값 변경
    if start == end:
        arr[idx] = value
        tree[node] = value
        return

    mid = (start + end) // 2
    update(node * 2, start, mid, idx, value)
    update(node * 2 + 1, mid + 1, end, idx, value)

    tree[node] = tree[node * 2] + tree[node * 2 + 1]

build(1, 0, N - 1)

for _ in range(Q):
    x, y, a, b = map(int, input().split())

    # x, y 순서 보정
    if x > y:
        x, y = y, x

    # 1-based -> 0-based
    result = query(1, 0, N - 1, x - 1, y - 1)
    print(result)

    update(1, 0, N - 1, a - 1, b)
