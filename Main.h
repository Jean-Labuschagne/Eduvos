#include <iostream>
#include <string>
#include <fstream>
#include <limits>

using namespace std;

const double DISCOUNT_RATE = 0.10;
const double DISCOUNT_THRESHOLD = 100.00;

const double COFFEE_PRICE = 15.00;
const double SANDWICH_PRICE = 30.00;
const double SALAD_PRICE = 25.00;
const double JUICE_PRICE = 10.00;
const double MUFFIN_PRICE = 20.00;
const double PIZZA_PRICE = 35.00;
const double SOUP_PRICE = 18.00;
const double BURGER_PRICE = 40.00;

int main() {
    string name, surname;
    int menuItem;
    int numItems = 0;
    double total = 0;
    double discount = 0;

    cout << "Please enter your name \n";
    cin >> name;
    cout << "Please enter your surname \n";
    cin >> surname;
    cout << "Menu: \n 1.Coffee - R15.00 \n 2.Sandwich - R30.00 \n 3.Salad - R25.00 \n 4.Juice - R10.00 \n 5.Muffin - R20.00 \n 6.Pizza Slice - R35.00 \n 7.Soup - R18.00 \n 8.Burger - R40.00";

    do {
        cout << "\n Enter a number between 1-8 corresponding with the menu.\n Enter up to 8 items \n Enter 0 to exit \n";
        cin >> menuItem;
        if (cin.fail()) {
            cout << "Invalid input\n";
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
            menuItem = -1;
        }
        else if (menuItem >= 0 && menuItem < 9) {
            switch (menuItem) {
            case 1: total += COFFEE_PRICE; 
                break;
            case 2: total += SANDWICH_PRICE; 
                break;
            case 3: total += SALAD_PRICE; 
                break;
            case 4: total += JUICE_PRICE; 
                break;
            case 5: total += MUFFIN_PRICE; 
                break;
            case 6: total += PIZZA_PRICE; 
                break;
            case 7: total += SOUP_PRICE; 
                break;
            case 8: total += BURGER_PRICE; 
                break;
            case 0: break;
            default: cout << "Invalid input\n"; 
                break;
            }
            if (menuItem != 0) {
                numItems++;
                cout << "\n Your total is: " << total << "\n";
            }
        }
        else {
            cout << "Invalid input\n";
        }
    } while (menuItem != 0);

    if (total > DISCOUNT_THRESHOLD) {
        discount = total * DISCOUNT_RATE;
    }

    double finalTotal = total - discount;

    cout << "\nTotal bill: " << total << endl;
    if (discount > 0) {
        cout << "Discount applied: " << discount << endl;
    }
    cout << "Final bill after discount: " << finalTotal << endl;

    ofstream outFile("CafeteriaBill.txt");
    if (outFile.is_open()) {
        outFile << "Name: " << name << "\n";
        outFile << "Surname: " << surname << "\n";
        outFile << "Final Total Bill: " << finalTotal << "\n";
        outFile.close();
        cout << "The bill has been written to CafeteriaBill.txt\n";
    }
    else {
        cout << "Error: Could not open file.\n";
    }

    return 0;
}
