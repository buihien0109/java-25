const questions = [
    {
        id: 1,
        title: "1 + 1 = ?",
        choices: [1, 2, 3, 4, 5],
        answer: 2
    },
    {
        id: 2,
        title: "Năm 2024 có phải năm nhuận không?",
        choices: ["Có", "Không"],
        answer: "Có"
    }
];

let currentQuestionIndex = 0;

const renderQuestion = () => {
    const question = questions[currentQuestionIndex];

    // Hiển thị câu hỏi
    const titleEl = document.querySelector("#question p");
    titleEl.innerHTML = `Câu ${currentQuestionIndex + 1}: ${question.title}`;

    // Hiển thị các lựa chọn
    const choicesEl = document.querySelector(".choices");
    let html = "";
    question.choices.forEach((choice, index) => {
        html += `
            <div class="choice-item">
                <input type="radio" name="choice" id="${index}" value="${choice}" />
                <label for="${index}">${choice}</label>
            </div>
        `;
    });
    choicesEl.innerHTML = html;
};

renderQuestion();