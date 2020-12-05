#include <iostream>
#include <vector>
#include <fstream>
#include <string>
#include <iterator>
#include <regex>

using namespace std;

template <class Container>
void split(const string& str, Container& cont, string delim = "\n\n"){
    size_t current, previous = 0;
    current = str.find(delim);
    while (current != string::npos) {
        cont.push_back(str.substr(previous, current - previous));
        previous = current + 1;
        current = str.find(delim, previous);
    }
    cont.push_back(str.substr(previous, current - previous));
}

bool validValues(string& w, string& toSearch, int index) {
    int val;
    if (toSearch == "byr:") {
        val = stoi(w.substr(index, 4));
        return val >= 1920 && val <= 2002;
    } else if (toSearch == "iyr:") {
        val = stoi(w.substr(index, 4));
        return val >= 2010 && val <= 2020;
    } else if (toSearch == "eyr:") {
        val = stoi(w.substr(index, 4));
        return val >= 2020 && val <= 2030;
    } else if (toSearch == "hgt:") {
        if (w.find("cm") != -1) {
            val = stoi(w.substr(index, 3));
            return val >= 150 && val <= 193;
        } else {
            val = stoi(w.substr(index, 2));
            return val >= 59 && val <= 76;
        }
    } else if (toSearch == "hcl:") {
        return regex_match(w.substr(index, 7), regex("#([0-9a-f]){6}"));
    } else if (toSearch == "ecl:") {
        return regex_match(w.substr(index, 3), regex("amb|blu|brn|gry|grn|hzl|oth"));
    } else if (toSearch == "pid:") {
        string end = w.substr(index + 9, 1);
        bool isValidEnd = end == "" || end == " " || end == "\n";
        return regex_match(w.substr(index, 9), regex("[0-9]{9}")) && isValidEnd;
    } else {
        return false;
    }
}

void solve1(vector<string> v, vector<string> valid) {
    int count = 0;
    for (auto& s: v) {
        bool missing = false;
        for (auto& toSearch : valid) {
            if (s.find(toSearch) == -1) {
                missing = true;
                break;
            }

        }
        if (!missing) ++count;
    }
    cout << count;
}

void solve2(vector<string> v, vector<string> valid) {
    int count = 0;
    vector<int> temp;
    int i = -1;
    for (auto& s: v) {
        i++;
        bool missing = false;
        for (auto& toSearch : valid) {
            int val = s.find(toSearch);
            if (val == - 1) {
                missing = true;
                break;
            }
            if (!validValues(s ,toSearch, val + 4)) {
                missing = true;
                break;
            }
        }
        if (!missing) ++count;
    }
    cout << count;
}

int main() {
    ifstream ifs("/Users/sean/CLionProjects/untitled/day4.txt");
    string content((istreambuf_iterator<char>(ifs)),
                   (istreambuf_iterator<char>()));
    vector<string> v;
    split(content, v);
    vector<string> valid = {"byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:"};
    solve1(v, valid);
    cout << "\n";
    solve2(v, valid);
    return 0;
}

