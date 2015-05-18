#include <iostream>
#include <string>
#include <cmath>
#include <vector>
#include <algorithm>
#include <iterator>

using namespace std;

long next_number(long n, long b) {
	string number = to_string(n);
	long result = 0;
	for (char c : number) {
		result += pow(c - '0', b);
	}
	return result;
}

int main(int argc, char const *argv[])
{
	if (argc < 3) {
		cout << "Not enough args" << endl;
		exit(1);
	}
	long b = stoi(argv[1]);
	long n = stoi(argv[2]);
	vector<long> numbers;
	bool found = false;
	decltype(numbers.begin()) it;
	while (!found) {
		n = next_number(n, b);
		it = find(numbers.begin(), numbers.end(), n);
		found = it != numbers.end() || n == 1;
		numbers.push_back(n);
	}
	numbers.erase(numbers.begin(), ++it);
	copy ( numbers.begin(), numbers.end(), ostream_iterator<long>(cout, ", ") );
	cout << endl;
	return 0;
}