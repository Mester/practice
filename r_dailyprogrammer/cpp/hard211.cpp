#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>

int is_happy2 (int a, int b){
    if(a > b){
        return 1;
    }else if(a < b){
        return -1;
    }
    return 0;
}

int is_happy3 (int a, int b, int c){
  if(a > b && c > b){
      return -1;
  }else if(a < b && c < b){
      return 1;
  }
  return 0;
}

int happy_value(std::vector<int> v){
    int happy_value = 0;
    for (unsigned int i = 0;i != v.size() ; ++i) {
        if(i == 0){
            happy_value += is_happy2(v[i], v[i+1]);
        }else if(i == v.size()-1){
            happy_value += is_happy2(v[i], v[i-1]);
        }else{
            happy_value += is_happy3(v[i-1], v[i], v[i+1]);
        }
    }
    return happy_value;
}

struct mycompareclass {
  bool operator() (std::vector<int> v1, std::vector<int> v2) {
    return happy_value(v1) < happy_value(v2);
  }
} mycompare;

int main(int argc, char** argv)
{
    if(argc == 0) exit(0);

    std::vector<int> treats;
    std::vector<std::vector<int>> ordering;
    for (int i = 1; i != argc; ++i) {
        int n = argv[i][0] - '0';
        treats.push_back(n);
    }

    std::sort(treats.begin(), treats.end());

    do {
        ordering.push_back(treats);
    } while (std::next_permutation(treats.begin(), treats.end()));

    std::sort(ordering.begin(), ordering.end(), mycompare);
    std::cout << happy_value(ordering.back()) << std::endl;
    std::copy (ordering.back().begin(), ordering.back().end(), std::ostream_iterator<int>(std::cout, " "));
    std::cout << std::endl;
}
