function checkLength(mes) {

    var textbox = document.getElementById("searchbox").value.length;
    if (textbox >= 3) {
        document.location.href = mes.href
    }
    document.getElementById("length_error").style.display = "inline";
}

function checkForm(form) {

    var name_length = document.getElementById("name").value.length;
    var description_length = document.getElementById("desc").value.length;
    var price_length = document.getElementById("price").value.length;

    var name_error = document.getElementById("name_length_error");
    var description_error = document.getElementById("desc_length_error");
    var price_error = document.getElementById("price_length_error");

    if (name_length === 0 || description_length === 0 || price_length === 0) {
        if (name_length === 0) {
            name_error.style.display = "inline";
        } else {
            name_error.style.display = "none";
        }
        if (description_length === 0) {
            description_error.style.display = "inline";
        } else {
            description_error.style.display = "none";
        }
        if (price_length === 0) {
            price_error.style.display = "inline";
        } else {
            price_error.style.display = "none";
        }
    } else {
        document.location.href = form.href
    }
}
