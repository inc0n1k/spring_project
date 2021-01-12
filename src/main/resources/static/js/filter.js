'use strict'

filter()

/*function filter() {
    let category = document.getElementById('category');
    let name = document.getElementById('name');
    let minp = document.getElementById('minp');
    let maxp = document.getElementById('maxp');
    let products = document.getElementById('products');
    products.innerText = '';
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'filter');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=utf-8');
    xhr.send('name=' + (name.value === undefined ? "" : name.value) + '&category=' + category.value + '&minp=' + minp.value + '&maxp=' + maxp.value);
    xhr.onload = () => {
        let result = JSON.parse(xhr.response);
        for (let el of result) {
            let div_el = document.createElement('div');
            let div_name = document.createElement('div');
            let div_price = document.createElement('div');
            let div_separator = document.createElement('div');
            div_separator.innerText = '**********************';
            div_name.innerText = el.name;
            div_price.innerText = el.price;
            div_el.append(div_separator);
            div_el.append(div_name);
            div_el.append(div_price);
            products.append(div_el);
        }
    }
} /!*close filter*!/*/

//work
function filter() {
    let products = document.getElementById('products');
    products.innerText = '';
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'filter');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=utf-8');
    xhr.send('arg= ' + JSON.stringify(createPathString()));
    xhr.onload = () => {
        products.prepend('**********************');
        let result = JSON.parse(xhr.response);
         for (let el of result) {
             let div_el = document.createElement('div');
             let div_name = document.createElement('div');
             let div_price = document.createElement('div');
             div_name.innerText = el.name;
             div_price.innerText = el.price;
             div_el.append(div_name);
             div_el.append(div_price);
             div_el.append('**********************');
             products.append(div_el);
         }
    }
} /*close filter*/

//work
function getValues(el) {
    values.innerHTML = '';
    if (el.value !== '') {
        let xhr = new XMLHttpRequest();
        xhr.open('POST', 'filter/getvalues');
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=utf-8');
        xhr.send('id=' + el.value);
        xhr.onload = () => {
            let result = JSON.parse(xhr.response);
            for (let el in result) {
                if (result[el].length !== 0) {
                    let div_container = document.createElement('div');
                    div_container.id = 'div_container';
                    let div_name = document.createElement('div');
                    div_container.append(div_name);
                    div_name.innerText = el;
                    for (let val in result[el]) {
                        let input = document.createElement('input');
                        input.type = 'checkbox';
                        input.name = result[el][val];
                        input.value = val;
                        div_container.append(input);
                        div_container.append(val);
                        div_container.append(document.createElement('br'));
                    }
                    values.append(div_container);
                }
            }
        }
    }
}/*close getValues*/

//work
submit.onclick = (e) => {
    e.preventDefault();
    e.stopPropagation();
    filter();
}

//work
reset.onclick=(e)=>{
    e.preventDefault();
    e.stopPropagation();
    window.location.href='filter';
}

//work
function createPathString() {
    let res = [];
    for (let el of new FormData(submit.parentNode.parentNode).entries()) {
        let t_obj = {};
        t_obj.name = el[0];
        t_obj.value = el[1];
        res.push(t_obj);
    }
    return res;
}