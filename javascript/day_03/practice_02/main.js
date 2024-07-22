// Câu 1. Tạo 1 thẻ p có id=“text”, có nội dung bất kỳ (có thể tạo bằng HTML hay Javascript - tùy chọn). Yêu cầu

let p1 = document.getElementById("text")
p1.style.color = "#777"
// Đặt kích thước phông chữ thành 2rem
p1.style.fontSize = "2rem"
// Đặt nội dung HTML thành Tôi có thể làm <em> bất cứ điều gì </em> tôi muốn với JavaScript.
p1.innerHTML = "Tôi có thể làm <em> bất cứ điều gì </em> tôi muốn với JavaScript."


// Câu 2. Sử dụng vòng lặp để đặt màu chữ cho tất cả thẻ li thành màu blue (tạo thẻ ul-li bằng html)
// const myList = document.querySelectorAll("#text + ul li");
const myList = document.querySelectorAll("#list-1 li");
myList.forEach(item => {
    item.style.color = 'blue';
});

// Câu 3. Sử dụng javascript để thực hiện những công việc sau

// 1. Thêm 3 thẻ <li> có nội dung Item 8, Item 9, Item 10 vào cuối danh sách
const list = document.getElementById("list");
// const newItem8 = document.createElement('li');
// newItem8.textContent = 'Item 8';
// list.appendChild(newItem8);

// const newItem9 = document.createElement('li');
// newItem9.textContent = 'Item 9';
// list.appendChild(newItem9);

// const newItem10 = document.createElement('li');
// newItem10.textContent = 'Item 10';
// list.appendChild(newItem10);

for (let i = 8; i < 11; i++) {
    const newItem = document.createElement('li');
    newItem.textContent = `Item ${i}`;
    list.appendChild(newItem);
}

// 2. Sửa nội dung cho thẻ <li> 1 thành màu đỏ (color)
list.children[0].style.color = "red"

// 3. Sửa background cho thẻ <li> 3 thành màu xanh (background-color)
list.children[2].style.backgroundColor = "blue"

// 4. Remove thẻ <li> 4
const listItemToRemove = list.querySelector("li:nth-child(4)");
listItemToRemove.parentElement.removeChild(listItemToRemove);

// 5. Thêm thẻ <li> mới thay thế cho thẻ <li> 4 bị xóa ở bước trước, thẻ <li> mới có nội dung bất kỳ
// const newListItem = document.createElement("li");
// newListItem.textContent = "Nội dung mới";

// list.children[2].insertAdjacentElement("afterend", newListItem);
// list.children[3].insertAdjacentElement("beforebegin", newListItem);

list.children[2].insertAdjacentHTML("afterend", "<li>Nội dung mới</li>");