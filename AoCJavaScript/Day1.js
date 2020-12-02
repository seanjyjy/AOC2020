let fs = require("fs");
let text = fs.readFileSync("input.txt").toString().split("\n");
let text2 = fs.readFileSync("input2.txt").toString().split("\n");

const twoSum = () => {
  let set = new Set();
  let target = 2020;
  for (let i = 0; i < text.length; i++) {
    if (set.has(target - text[i])) {
      console.log(text[i] * (target - text[i]));
      break;
    }
    set.add(parseInt(text[i]));
  }
};

const threeSum = () => {
  for (let i = 0; i < text.length - 2; i++) {
    let set = new Set();
    let first = parseInt(text2[i]);
    let sum = 2020 - first;
    for (let j = i + 1; j < text.length; j++) {
      let second = parseInt(text2[j]);
      if (set.has(sum - second)) {
        console.log(first * second * (2020 - first - second));
        break;
      }
      set.add(parseInt(text2[j]));
    }
  }
};

threeSum();
