#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>

using namespace std;

template <class Container>
void split(const string& str, Container& cont, string delim = "\n"){
    size_t current, previous = 0;
    current = str.find(delim);
    while (current != string::npos) {
        cont.push_back(str.substr(previous, current - previous));
        previous = current + 1;
        current = str.find(delim, previous);
    }
    cont.push_back(str.substr(previous, current - previous));
}

int getID(string s) {
    int lowRow = 0, highRow = 127, i, midRow, lowCol = 0, highCol = 7, midCol;
    for (i = 0; i < 10; ++i) {
        if (s[i] == 'F') {
            midRow = (lowRow + highRow) / 2;
            highRow = midRow;
        } else if (s[i] == 'B') {
            // round up
            midRow = (lowRow + highRow + 1) / 2;
            lowRow = midRow;
        } else if (s[i] == 'R') {
            // round up
            midCol = (lowCol + highCol + 1) / 2;
            lowCol = midCol;
        } else if (s[i] == 'L') {
            midCol = (lowCol + highCol) / 2;
            highCol = midCol;
        }
    }
    return midRow * 8 + midCol;
}

void solve1(vector<string> v) {
    int highest = 0;
    for (auto& s: v) {
        highest = max(getID(s), highest);
    }
    cout << highest << "\n";
}

void solve2(vector<string> v) {
    vector<int> idList;
    for (auto& s: v) {
        idList.push_back(getID(s));
    }
    sort(idList.begin(), idList.end());
    int i = idList.front(), j, ans = 0;
    for (j = 0; j < idList.size(); ++j) {
        if (idList[j] != i) {
            ans = i;
            break;
        }
        ++i;
    }
    cout << ans << "\n";
}

int main() {
    ifstream ifs("/Users/sean/Desktop/SeanP/AoC/AoCCpp/day5.txt");
    string content((istreambuf_iterator<char>(ifs)),
                   (istreambuf_iterator<char>()));
    vector<string> v;
    split(content, v);
    solve1(v);
    solve2(v);
    return 0;
}