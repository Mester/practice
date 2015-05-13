#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <map>

using namespace std;

void fill(int* canvas, int rows, int color, int x, int y, int width, int height){
	//cout << "x=" << x << " y=" << y << " width=" << width << " height=" << height << endl;
	for(int i = x; i != x+width; ++i){
		for(int j = y; j != y+height; ++j){
		//	if(i > 100 || j > 100){
		//	cout << "i=" << i << " j=" << j;
		//	cout << " x+width=" << x+width << endl;
		//	}
			canvas[i*rows + j] = color;
		}
	}
}

void print_canvas(int* canvas, int rows, int cols){
	for(int j = 0; j != rows; ++j){
		for(int i = 0; i != cols; ++i){
			cout << canvas[i*rows + j] << " ";
		}
		cout << endl;
	}
}

void print_colors(int* canvas, int rows, int cols){
	map<int, int> colorSums;
	for(int j = 0; j != rows; ++j){
		for(int i = 0; i != cols; ++i){
			colorSums[canvas[i*rows + j]] += 1;
		}
	}
	for(auto pair : colorSums){
		cout << pair.first << " " << pair.second << endl;
	}
}

int main(int argc, char** argv)
{
	if( argc < 2 ){
		cout << "No input file" << endl;
		exit(1);
	}
	
	ifstream in;
	in.open(argv[1]);
	int cols, rows;
	in >> cols >> rows;
	int *canvas = new int[cols*rows]; // canvas[i*rows + j] == canvas[i][j]
	
	fill(canvas, rows, 0, 0, 0, cols, rows);
	//print_canvas(canvas, rows, cols);
	
	string line;
	getline(in, line); // just to bypass the remaining empty text on first line
	int lineNbr = 0;
	while( getline(in, line) ) {
		//print_canvas(canvas, rows, cols);
		stringstream ss(line);
		int color, col, row, width, height;
		ss >> color >> col >> row >> width >> height;
		//cout << color << row << col << width << height << endl;
		//cout << ++lineNbr << endl;
		fill(canvas, rows, color, row, col, width, height);
	}
	print_canvas(canvas, rows, cols);
	print_colors(canvas, rows, cols);
	
}