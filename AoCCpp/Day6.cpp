#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>
#include <unordered_set>
#include <unordered_map>
using namespace std;

template <class Container>
void split(const string& str, Container& cont, string delim){
    size_t current, previous = 0;
    int size = delim.length() - 1;
    current = str.find(delim);
    while (current != string::npos) {
        cont.push_back(str.substr(previous, current - previous));
        previous = current + 1 + size;
        current = str.find(delim, previous);
    }
    cont.push_back(str.substr(previous, current - previous));
}

int getUnique(string s) {
    unordered_set<char> set;
    vector<string> listOfStrings;
    split(s, listOfStrings, "\n");
    for (auto& string1 : listOfStrings) {
        int i;
        for (i = 0; i < string1.length(); ++i)
            set.insert(string1[i]);
    }
    return set.size();
}

void solve1(vector<string> v) {
    int sum = 0;
    for (auto& s : v)
        sum += getUnique(s);
    cout << sum;
}

int getSameAnswer(vector<string> listOfStrings) {
    unordered_map<char, int> map;
    int size = listOfStrings.size();
    for (auto& string1 : listOfStrings) {
        int i;
        for (i = 0; i < string1.length(); ++i) {
            auto val = map.find(string1[i]);
            if (val != map.end()) {
                val->second = val->second + 1;
            }
            else {
                map.insert(pair<char, int>(string1[i], 1));
            }
        }
    }
    int sum = 0;
    for (auto& keyPair : map)
        if (keyPair.second == size) sum++;
    return sum;
}

void solve2(vector<string> v) {
    int sum = 0;
    int j = 0;
    for (auto& s : v) {
        vector<string> listOfStrings;
        split(s, listOfStrings, "\n");
        if (listOfStrings.size() == 1)
             sum += listOfStrings[0].length();
        else sum += getSameAnswer(listOfStrings);

    }
    cout << sum;
}

int main() {
    ifstream ifs("/Users/sean/Desktop/SeanP/AoC/AoCCpp/day6.txt");
    string content((istreambuf_iterator<char>(ifs)),
                   (istreambuf_iterator<char>()));
    vector<string> v;
    split(content, v, "\n\n");
    // solve1(v);
    solve2(v);
    return 0;
}