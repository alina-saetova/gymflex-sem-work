function remove(id) {
    document.getElementById(id).remove();
}

function delete_from_db(obj_id, obj_type) {
    $.ajax({
        type: "POST",
        url: "/delete_article",
        data: {
            type: obj_type,
            id: obj_id
        }
    })
}

function change_password() {
    $.ajax({
        type: "POST",
        url: "/change_password",
        data: {
            oldpassword: $("#old_password").val(),
            newpassword: $('#new_password').val()
        },
        success:function (msg) {
            if (msg == 1) {
                alert("успешно сохранено")
            }
            else {
                alert("введен неправильный старый пароль")
            }
        }
    })
}
