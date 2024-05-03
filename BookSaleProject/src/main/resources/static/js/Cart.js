function toggleCheckboxes(source) {
    checkboxes = document.getElementsByName('checkBoxNoLabel');
    for (var i = 0, n = checkboxes.length; i < n; i++) {
        checkboxes[i].checked = source.checked;
    }
    calculateOrderTotal();
}

var selectedIds = [];


function calculateOrderTotal() {
    var checkboxes = document.getElementsByName('checkBoxNoLabel');
    var orderTotal = 0;
    selectedIds = [];

    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            selectedIds.push(checkboxes[i].id);
            var price = parseFloat(checkboxes[i].parentNode.parentNode.querySelector('.price').innerText);
            orderTotal += price;
        }
    }
    
    var orderTotalElement = document.querySelector('.order-total');
    if (orderTotalElement) {
        orderTotalElement.innerText = orderTotal + 'đ';
    }


}

// Hàm để xử lý sự kiện khi nút thanh toán được nhấn
function handlePayment() {
    // Chuyển mảng selectedIds sang chuỗi để gửi đến servlet
    var selectedIdsString = selectedIds.join(",");

    // Gửi các ID đã chọn đến servlet bằng cách chuyển hướng URL
    window.location.href = "/bill/viewPayment?selectedIds=" + selectedIdsString;
}
