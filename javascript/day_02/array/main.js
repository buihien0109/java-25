// Khai báo array rỗng
let arr = [];

// Khai báo array
let number = [];

// Gán giá trị cho các phần tử của array thông qua chỉ số
number[0] = 1;
number[1] = "Bùi Hiên";
number[2] = true;

// Khai báo và khởi tạo giá trị cho array
let myArr = [1, 2, 3, 4, true, false, "Nguyễn Văn A"];
myArr.fo

// Bài 1: Viết function tìm số lớn nhất trong mảng
// Sắp xếp
// So sánh -> gán
const findMax = (arr) => {
    arr.sort((a, b) => b - a);
    return arr[0];
}

const findMax2 = (arr) => {
    let max = arr[0];
    arr.forEach((value, index) => {
        if(value > max) {
            max = value;
        }
    })
    return max;
}

// Math.max(1,32,52,2,5,2)
const findMax3 = (arr) => {
    return Math.max(...arr); // Cú pháp ES6: Spread operator, Rest parameter
}

console.log(findMax([4, 2, 6, 7, 3, 10, 0]));
console.log(findMax2([4, 2, 6, 7, 3, 10, 0]));

// Bài 3: Viết function cho phép truyền vào 1 mảng các số, kết quả trả về là 1 mảng mới với các số là số dư tương ứng khi chia mảng cũ cho 2
// Ví dụ : [4,2,5,6,2,7] => [0,0,1,0,0,1]
// Tạo mảng rỗng để chứa kết quả
// Vòng for -> Chia lấy dư cho 2 -> push vào mảng kết quả
const modulo2 = (arr) => {
    let rs = [];
    arr.forEach((value) => {
        rs.push(value % 2);
    })
    return rs;
}

const modulo22 = (arr) => {
    // const result = arr.map(e => e % 2)
    // return result

    return arr.map((value) => value % 2);
}




