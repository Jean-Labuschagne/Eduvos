menu = ["coffee", "tea", "muffin", "sandwich"]

stock = {
    "coffee": 50,
    "tea": 40,
    "muffin": 25,
    "sandwich": 30
}

price = {
    "coffee": 3.50,
    "tea": 2.50,
    "muffin": 4.00,
    "sandwich": 6.50
}

total_stock = 0
for item in menu:
    total_stock += stock[item] * price[item]

print(f"Total stock value: R{total_stock:.2f}")
