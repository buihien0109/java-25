const sayHello = () => {
    alert("Xin chào các bạn 1");
};

const btn2 = document.getElementById("btn2");
btn2.onclick = () => {
    alert("Xin chào các bạn 2");
}

const btn3 = document.getElementById("btn3");
btn3.addEventListener("click", () => {
    alert("Xin chào các bạn 3");
});

const btnPlay = document.getElementById("play");
const btnPause = document.getElementById("pause");
const timeEl = document.getElementById("time");
const messageEl = document.getElementById("message");

let time = 10;
let interval;

btnPlay.addEventListener("click", () => {
    interval = setInterval(() => {
        time--;
        timeEl.innerText = `${time}s`;

        if (time === 0) {
            messageEl.innerText = "Hết giờ";
            clearInterval(interval);
        }
    }, 1000) // 1000ms = 1s
});

btnPause.addEventListener("click", () => {
    clearInterval(interval);
});

// document.addEventListener("click", (e) => {
//     const currentEl = document.querySelector(".circle");
//     if (currentEl) {
//         currentEl.parentElement.removeChild(currentEl);
//     }

//     const circleEl = document.createElement("div");
//     circleEl.classList.add("circle");

//     // Set position for circle
//     circleEl.style.left = `${e.offsetX - 50}px`;
//     circleEl.style.top = `${e.offsetY - 50}px`;

//     document.body.appendChild(circleEl);
// })

document.addEventListener("click", (e) => {
    const currentEl = document.querySelector(".circle");
    if (currentEl) {
        currentEl.style.left = `${e.offsetX - 50}px`;
        currentEl.style.top = `${e.offsetY - 50}px`;
        return
    }

    const circleEl = document.createElement("div");
    circleEl.classList.add("circle");

    // Set position for circle
    circleEl.style.left = `${e.offsetX - 50}px`;
    circleEl.style.top = `${e.offsetY - 50}px`;

    document.body.appendChild(circleEl);
})

// Tìm kiếm user
const users = [
    { id: 1, name: "Trần Hùng Anh" },
    { id: 2, name: "Ngô Văn Tuấn" },
    { id: 3, name: "Trịnh Thu Hà" },
    { id: 4, name: "Bùi Văn Hiên" },
    { id: 5, name: "Nguyễn Thu Hằng" },
    { id: 6, name: "Ngô Xuân An" },
    { id: 7, name: "Bùi Bằng Đoàn" },
];

const inputEl = document.getElementById("input-name");
const btnShowAll = document.getElementById("btn-show-all");
const listUserEl = document.getElementById("list");

const renderUsers = (users) => {
    let html = "";
    users.forEach(user => {
        html += `<li>${user.id} - ${user.name}</li>`;
    });
    listUserEl.innerHTML = html;
};

inputEl.addEventListener("keydown", (e) => {
    console.log(e);
    if (e.key === "Enter") {
        // Lấy keyword từ input
        const keyword = e.target.value;

        // Lọc ra những user có tên chứa keyword
        const result = users.filter(user => user.name.toLowerCase().includes(keyword.toLowerCase()));

        // Hiển thị kết quả
        renderUsers(result);
    }
})

btnShowAll.addEventListener("click", () => {
    renderUsers(users);
});

renderUsers(users);

// Scroll back to top
const btnTop = document.getElementById("back-to-top");
window.addEventListener("scroll", () => {
    if (document.documentElement.scrollTop > 300) {
        // Hiển thị nút back to top
        btnTop.classList.remove("hide");
    } else {
        // Ẩn nút back to top
        btnTop.classList.add("hide");
    }
});

btnTop.addEventListener("click", () => {
    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
});