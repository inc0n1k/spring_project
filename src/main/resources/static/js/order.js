'use strict'

function orderClose(el) {
    if (el.checked) {
        let form = document.querySelector('form');
        let div_complite = document.createElement('div');
        div_complite.id = 'save';

        let t_input = document.createElement('input');
        t_input.type = 'radio';
        t_input.name = 'complite';
        t_input.setAttribute('onchange','commText(this)');
        let f_input = t_input.cloneNode(true);

        t_input.value = 'true';
        t_input.checked = true;
        div_complite.append(t_input);
        div_complite.append('Complite...')

        f_input.value = 'false';
        f_input.checked = false;
        div_complite.append(f_input);
        div_complite.append(' Don\'t complite');

        let div_btn = document.createElement('div');
        div_btn.id = 'div_btn';
        let button = document.createElement('button');
        button.type = 'submit';
        button.innerText = 'Close order';
        div_btn.append(button);
        div_complite.append(div_btn);

        form.append(div_complite);

    } else {
        save.remove();
    }
}

function commText(el) {
    if (el.value === 'true') {
        commDiv.remove();
    } else {
        let div = document.createElement('div');
        div.id = 'commDiv';
        let text = document.createElement('textarea');
        text.name = 'comment';
        text.required = true;
        div.append(text);
        div_btn.before(div);
    }
}