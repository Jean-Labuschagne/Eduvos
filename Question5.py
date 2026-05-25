from heapq import heappop, heappush

text = "rhaenyra"

freq = {}
for ch  in text:
    freq[ch] = freq.get(ch, 0) + 1

heap = []
for ch, fr in freq.items():
    heappush(heap, (fr, ch))

while len(heap)> 1:
    f1, left = heappop(heap)
    f2, right = heappop(heap)
    heappush (heap, (f1+f2, (left, right)))

tree = heap[0][1]

codes = {}
def build_codes(node, code=""):
    if isinstance (node, str):
        codes[node] = code
        return
    left, right = node
    build_codes(left, code + "0")
    build_codes(right, code + "1")

build_codes(tree)

print("Frequencies:", freq)
print("Codes", codes)
print("Total Huffman bits:", sum(freq[ch] * len(codes[ch]) for ch in codes))