'use strict'

let edit_element;

function cancel() {
    let remove_button = document.getElementById(edit_element + '_button');
    let remove_form = document.getElementById(edit_element + '_form');
    if (remove_button !== null) remove_button.removeAttribute('disabled');
    if (remove_form !== null) remove_form.remove();
}

function showEditForm(element) {
    cancel();

    let product_div = document.getElementById(element.value + '_div');
    edit_element = element.value;
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'getproduct')
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=utf-8');
    xhr.send('id=' + element.value);
    xhr.onload = () => {
        let xhr_res = JSON.parse(xhr.response);

        // console.log('****************************');
        // console.log(xhr_res);
        // console.log('****************************');
        // console.log('Name: ' + xhr_res.name);
        // console.log('Price: ' + xhr_res.price);
        // console.log('-------------------');
        // for (let el of xhr_res.values) {
        //     console.log(el.option.name + ' : ' + el.value);
        //     console.log('-------------------');
        // }
        // console.log('****************************');

        //***Crete form*****
        let form = document.createElement('form');
        form.setAttribute('class', 'edit_form');
        form.id = element.value + '_form';
        form.action = 'edit';
        form.method = 'post';
        form.autocomplete = 'true';

        //***Create hide input
        let hide_input = document.createElement('input');
        hide_input.type = 'hidden';
        hide_input.name = 'id';
        hide_input.value = element.value;
        form.append(hide_input);

        //***Name**********
        let div_item = document.createElement('div');
        div_item.setAttribute('class', 'item');
        form.append(div_item);

        let div_name = document.createElement('div');
        div_name.setAttribute('class', 'div_name');
        div_name.innerHTML = 'Name:';
        div_item.append(div_name);

        let input = document.createElement('input');
        input.setAttribute('class', 'input');
        input.name = 'name';
        input.placeholder = xhr_res.name;
        input.type = 'text';
        div_item.append(input);

        //***Price**********
        div_item = document.createElement('div');
        div_item.setAttribute('class', 'item');
        form.append(div_item);

        div_name = document.createElement('div');
        div_name.setAttribute('class', 'div_name');
        div_name.innerHTML = 'Price:';
        div_item.append(div_name);

        input = document.createElement('input');
        input.setAttribute('class', 'input');
        input.name = 'price';
        input.placeholder = xhr_res.price;
        input.type = 'number';
        input.min = '1';
        div_item.append(input);

        //***Values************
        for (let el of xhr_res.values) {
            // console.log(el.option.name + ' : ' + el.value);
            div_item = document.createElement('div');
            div_item.setAttribute('class', 'item');
            form.append(div_item);

            div_name = document.createElement('div');
            div_name.setAttribute('class', 'div_name');
            div_name.innerHTML = el.option.name + ':';
            div_item.append(div_name);

            input = document.createElement('input');
            input.setAttribute('class', 'input');
            input.name = el.id + '_val';
            input.placeholder = el.value;
            input.type = 'text';
            div_item.append(input);
        }

        //***Buttons***********
        div_item = document.createElement('div');
        div_item.setAttribute('class', 'item');
        form.append(div_item);

        let button = document.createElement('button');
        button.setAttribute('class', 'button');
        button.type = 'submit';
        button.innerHTML = 'Save';
        div_item.append(button);

        input = document.createElement('input');
        input.setAttribute('class', 'button');
        input.type = 'button';
        input.value = 'Cancel';
        input.onclick = () => {
            cancel();
        }
        div_item.append(input);

        //***Add form**********
        product_div.after(form);
        //***Disable button****
        element.disabled = 'true';
    }
}