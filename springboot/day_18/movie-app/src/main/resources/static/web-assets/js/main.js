toastr.options = {
    "closeButton": false,
    "debug": false,
    "newestOnTop": false,
    "progressBar": false,
    "positionClass": "toast-top-right",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}

const logout = () => {
    axios.post('/api/auth/logout').then(res => {
        if (res.status === 200) {
            toastr.success("Đăng xuất thành công");
            setTimeout(() => {
                window.location.href = '/dang-nhap';
            }, 1500)
        }
    }).catch(err => {
        console.log(err);
        toastr.error("Đăng xuất thất bại");
    })
}

// check menu item active
const checkMenuItemActive = () => {
    const url = window.location.pathname;
    const menuItem = document.querySelector(`#main-menu .navbar-nav a[href="${url}"]`);
    if (menuItem) {
        menuItem.classList.add('active');
    }
};
checkMenuItemActive();