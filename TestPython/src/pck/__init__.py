a = set([1,2,3,4,5,6,7,8])
a.add("hello world")   
for item in a:
    print(item)
print(a)
b = set([2,4,6,8,10,"hello world"])
print(a.intersection(b))