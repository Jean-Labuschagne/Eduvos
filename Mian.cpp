#include<iostream>
#include<fstream>
#include <string>

using namespace std;

void Menu();
void AddMovie();
void SearchMovie();
void ViewAllMovies();

ofstream OutputFile;
ifstream InputFile;


int main() {
	int menuOption;
	


	do {
		cout << endl;
		Menu();
		cout << endl;
		cout << " Enter your choice: ";
		cin >> menuOption;

		switch (menuOption)
		{

		case 1: AddMovie();
			break;
		case 2: SearchMovie();
			break;
		case 3: ViewAllMovies();
			break;
		case 4: cout << "Exiting system";
			break;
		default: cout << "Invalid input";
			break;

		}

	} while (menuOption != 4);

	return 0;
}

void AddMovie() {
	string title, director, genre, availability;
	OutputFile.open("Movie.txt", ios::app);

	if (OutputFile.is_open()) {
		cout << "enter title";
		getline(cin >> ws, title);
		cout << "enter director";
		getline(cin >> ws, director);
		cout << "enter genre";
		getline(cin >> ws, genre);
		cout << "Is it availible (yes/no)";
		getline(cin >> ws,availability);

		OutputFile << title << "#" << director << "#" << genre << "#" << availability << endl;
		OutputFile.close();

		cout << "Movie added"<<endl;
		
	}
	else {
		cout << "couldn't open file";
	}
	

}
void SearchMovie() {

	string searchItem;
	bool isFound = false;
	string title, director, genre, availability;
	int movieCounter;
	int delimiterPos;
	string lines;

	cout << "Enter movie name";
	getline(cin >> ws, searchItem);
	
	InputFile.open("Movie.txt");
	if (InputFile.is_open()) {
		while (getline(InputFile, lines) && !isFound)
		{
			delimiterPos = lines.find("#");
			title = lines.substr(0, delimiterPos);
			lines = lines.erase(0, delimiterPos + 1);

			if (title == searchItem) {
				isFound = true;

				delimiterPos = lines.find("#");
				director = lines.substr(0, delimiterPos);
				lines = lines.erase(0, delimiterPos + 1);

				delimiterPos = lines.find("#");
				genre = lines.substr(0, delimiterPos);
				lines = lines.erase(0, delimiterPos + 1);

				availability = lines;

				cout << "Movie found \n";
				cout << "Title: " << title << endl;
				cout << "Director: " << director << endl;
				cout << "Genre: " << genre << endl;
				cout << "Availability: " << availability << endl;

			}
		}
		if (!isFound) {
			cout << "Movie not found." << endl;
		}
		InputFile.close();
	}
	else {
		cout << "File could not open." << endl;
	}



}
void ViewAllMovies() {

	string title, director, genre, availability;
	int movieCounter = 0 ;
	int delimiterPos;
	string lines;




	InputFile.open("Movie.txt");
	if (InputFile.is_open()) {
		while (getline(InputFile, lines))
		{
			delimiterPos = lines.find("#");
			title = lines.substr(0, delimiterPos);
			lines = lines.erase(0, delimiterPos + 1);




			delimiterPos = lines.find("#");
			director = lines.substr(0, delimiterPos);
			lines = lines.erase(0, delimiterPos + 1);

			delimiterPos = lines.find("#");
			genre = lines.substr(0, delimiterPos);
			lines = lines.erase(0, delimiterPos + 1);

			availability = lines;

			movieCounter += 1;

			cout << "Movie" << movieCounter << ": ";
			cout << "Movie found \n";
			cout << "Title: " << title << endl;
			cout << "Director: " << director << endl;
			cout << "Genre: " << genre << endl;
			cout << "Availability: " << availability << endl;



		}
	}
	else {
		cout << "File could not open." << endl;
	}



}
void Menu() {
	cout << "Movie Rental Management Menu:" << endl;
	cout << "1. Add movie \n 2. Search movie \n 3. View all movies \n 4. Exit \n";
	
}