'use strict'

function onCh(el) {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'select');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=utf-8');
    xhr.send("id=" + el.value);
    xhr.onload = () => {
        let xhr_res = JSON.parse(xhr.response);
        // console.log(xhr_res);
        if (xhr_res.length !== 0) {
            let selects = document.querySelectorAll('select');
            for (let elem of selects) {
                if (elem.id >= xhr_res[0].level) {
                    elem.remove();
                }
            }
            document.querySelector('button').disabled = true;
            createCategoryList();

            function createCategoryList() {

                let select = document.createElement('select');
                select.setAttribute("class", "select_cat");
                select.id = xhr_res[0].level;
                select.onchange = () => {
                    onCh(select);
                };
                el.after(select);

                let option = document.createElement('option');
                option.innerText = 'Select category...';
                option.disabled = true;
                option.selected = true;
                select.append(option);

                for (let el of xhr_res) {
                    let option = document.createElement('option');
                    option.value = el.id;
                    option.innerText = el.category;
                    select.append(option);
                }
            }
        }
        if (xhr_res.length === 0) {
            document.querySelector('input').value = el.value;
            document.querySelector('button').disabled = false;
        }
    }
}