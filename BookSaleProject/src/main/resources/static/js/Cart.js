function toggleCheckboxes(source) {
    checkboxes = document.getElementsByName('checkBoxNoLabel');
    for (var i = 0, n = checkboxes.length; i < n; i++) {
        checkboxes[i].checked = source.checked;
    }
    calculateOrderTotal(source);
}

function calculateOrderTotal() {
    var checkboxes = document.getElementsByName('checkBoxNoLabel');
    var orderTotal = 0;

    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            var price = parseFloat(checkboxes[i].parentNode.parentNode.querySelector('.price').innerText);
            orderTotal += price;
        }
    }

    var orderTotalElement = document.querySelector('.order-total');
    if (orderTotalElement) {
        orderTotalElement.innerText = orderTotal + 'đ';
    }

    window.onload = toggleCheckboxes;
}