## Js là gì?

*JavaScript* là ngôn ngữ lập trình được nhà **phát triển sử dụng** để tạo trang web tương tác. Từ làm mới bảng tin trên trang mạng xã hội đến hiển thị hình ảnh động và bản đồ tương tác, các chức năng của JavaScript có thể `cải thiện trải nghiệm người dùng của trang web`

## JavaScript dùng để làm gì?
- Sửa đổi nội dung của một trang web.
- Thay đổi style và vị trí của các thành phần trên trang web.
- Phản hồi với các sự kiện từ người dùng.
- Thực hiện và kiểm soát việc chuyển tiếp các hình ảnh động.
- Thông báo cho người dùng.
- Thao tác với dữ liệu đầu vào và hiển thị kết quả sau khi xử lý.
- Xác thực dữ liệu đầu vào của người dùng trước khi gửi nó đến máy chủ.

## Demo code

```javascript
// In ra danh sách số lẻ trong mảng
const listOdd = function (arr, isOdd) {
    for (let i = 0; i < arr.length; i++) {
        if (isOdd(arr[i])) {
            console.log(arr[i]);
        }
    }
}

listOdd([1, 2, 3, 4, 5], isOdd)

// 3. Có thể được trả về từ functions khác
const funcA = function (a, b) {
    return function (c) {
        return a + b + c
    }
}

const funcReturnFromFuncA = funcA(4, 5); // Trả về function khi gọi funcA
console.log(funcReturnFromFuncA(6));
```

> Trước đây, các trang web có dạng tĩnh, tương tự như các trang trong một cuốn sách. Một trang tĩnh chủ yếu hiển thị thông tin theo một bố cục cố định và không làm được mọi thứ mà chúng ta mong đợi như ở một trang web hiện đại. JavaScript dần được biết đến như một công nghệ phía trình duyệt để làm cho các ứng dụng web linh hoạt hơn