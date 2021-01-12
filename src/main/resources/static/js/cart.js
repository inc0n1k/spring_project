'use strict'

function buttonDel(el) {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'removefromcart');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=utf-8');
    xhr.send('id=' + el.previousElementSibling.value);
    xhr.onload = () => {
        el.parentElement.parentElement.remove();
        calcCart();
        startCalculate();
    }
}

startCalculate();

function startCalculate() {
    let countElements = document.querySelectorAll('.count');
    let priceElements = document.querySelectorAll('.price');
    let sumElements = document.querySelectorAll('.sum');
    if (countElements.length === 0) {
        window.location.href = '/homepage';
    }
    for (let element of countElements) {
        element.value = getCount(+element.previousElementSibling.value);
    }
    for (let i = 0; i < countElements.length; i++) {
        sumElements[i].innerHTML = +priceElements[i].innerText * +countElements[i].value;
    }
    totalSum(sumElements);
}

function recalculate(el) {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'updatecart');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=utf-8');
    xhr.send('id=' + el.previousElementSibling.value + '&count=' + el.value);
    xhr.onload = () => {
        let count = +el.value;
        let price = +el.parentElement.previousElementSibling.innerHTML;
        el.parentElement.nextElementSibling.innerHTML = (price * count).toString();
        let elementsSum = document.querySelectorAll('.sum');
        totalSum(elementsSum);
    }
}

function totalSum(allSum) {
    let sum = 0;
    for (let elSum of allSum) {
        sum += +elSum.innerHTML;
    }
    let totalSum = document.querySelector('.totalSum');
    totalSum.innerText = sum;
}

function getCount(id) {
    let pos = getCookie("cart").lastIndexOf("{");
    let cartItems = JSON.parse(getCookie("cart"));
    if (pos === 1) {
        return cartItems[0].count;
    } else {
        cartItems = cartItems.find(item => item.id === id);
        return cartItems.count;
    }
}