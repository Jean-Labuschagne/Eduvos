patients = []

while True:
    print("\n1. Add New Patient \n2. Get next patient \n3. Display all patients \n4. Exit")
    option = input("Choose an option:")

    if option == "1":
        name = input("Enter patient's name and surname: ")
        patient_id = input("Enter patient's ID: ")
        condition = input("Enter patient's condition: ")

        patients.append({"name": name, "id": patient_id, "condition": condition})

    elif option == "2":
        if patients:
            next_patient = patients.pop(0)
            print(f"Next patient: {next_patient['name']}, ID: {next_patient['id']}, Condition: {next_patient['condition']}")
        else:
            print("No patients in the queue.")

    elif option == "3":
        for p in patients:
            print(f"Name: {p['name']}, ID: {p['id']}, Condition: {p['condition']}")
    elif option == "4":
        break