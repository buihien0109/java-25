console.log("Hello world");

// Khai báo biến và không gán giá trị cho biến
let age;
console.log(age); // undefined (primitive data)
age = 35;
console.log(age); // number 

// Khai báo biến và gán giá trị cho biến
let email = "hien@techmaster.vn";
console.log(email); // string

// Khởi tạo hằng số
const NUMBER = 10;
// NUMBER = 20;

// Template String (ES6)
let name = "Bùi Hiên"
let year = 1997

console.log(`Xin chào các bạn, mình tên là ${name}. Năm nay ${2022 - year} tuổi`);

// Không sử dụng template string
console.log("Xin chào các bạn, mình tên là " + name + ". Năm nay " + (2022 - year) + " tuổi");

// Function
// Tính tổng 2 số:
// c1: Regular function
function sum(a, b) {
    return a + b;
}

// c2: Function expression
let sum1 = function (a, b) {
    return a + b;
}

// c3: Arrow function (ES6) - (Gần giống Lambda function (Java 8))
let sum2 = (a, b) => {
    return a + b;
}

console.log(sum(10, 20));
console.log(sum1(11, 21));
console.log(sum2(12, 22));