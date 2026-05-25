class HashTable: 
    def __init__(self, size=10):
        self.size = size
        self.table = [[] for _ in range(size)]

    def hash_function(self, key):
        return sum(ord(c) for c in key) % self.size
        
    def insert(self, key, value):
        index = self.hash_function(key)
        for i (k, v) in enumerate(self.table[index]):
            if k == key:
                self.table[index][i] = (key, value)
                return
        self.table[index].append((key, value))

    def get(self, key):
        index = self.hash_function(key)
        for k, v in self.table[index]:
            if k == key:
                return v
        return None
    
    def display_table(self):
        for i, bucket in enumerate(self.table):
            print(f"Bucket {i}: {bucket}")

    def display_user(self, key):
        balance = self.get(key)
        if balance is not None:
            print(f"Username: {key}, Balance: R{balance:.2f}")
        else:
            print(f"User {key} not found.")

    def main():
        bank = HashTable()
        users = ["Scrooge Mcduck","Carlisle Cullen", "Smaug", "Richie Rich", "Toney Stark","Bruce Wayne","Jay Gatsby","Tywin Lanastir","Lex Luther","Chuck Bass","Christian Grey","Lara Croft"]

        for user in users:
            bank.insert(user, 10000.0)

        while True:
            print("\n1. Display table\n2. Display user \n3. Deposit \n4. Withdraw \n5. Exit")
            option = input("Choose an option:")

            if option == "1":
                bank.display_table()

            elif option == "2":
                user = input("Enter username: ")
                bank.display_user(user)

            elif option == "3":
                user = input("Enter username: ")
                amount = float(input("Enter deposit amount: "))
                balance = bank.get(user)
                if balance is not None:
                    bank.insert(user, balance + amount)
                    print(f"Deposited R{amount:.2f}. New balance: R{balance + amount:.2f}")
                else:
                    print(f"User {user} not found.")
            
            elif option == "4":
                user = input("Enter username: ")
                amount = float(input("Enter withdrawal amount: "))
                balance = bank.get(user)
                if balance is not None:
                    if amount <= balance:
                        bank.insert(user, balance - amount)
                        print(f"Withdrew R{amount:.2f}. New balance: R{balance - amount:.2f}")
                    else:
                        print(f"Insufficient funds. Current balance: R{balance:.2f}")
                else:
                    print(f"User {user} not found.")

            elif option == "5":
                break
    if __name__ == "__main__":
        main()