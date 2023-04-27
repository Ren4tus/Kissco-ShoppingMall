$(document).ready(function () {

    $("#update_btn").click(function (event) {

        event.preventDefault();

        let name = $("#input_name").val();
        let phone = $("#input_phone").val();
        let email = $("#input_email").val();
        let detail_address = $("#input_detail_address").val();
        let username = $("#input_username").val();


        let form = {
            detail_address: detail_address,
            name: name,
            phone: phone,
            email: email,
            username: username
        };

        console.log(JSON.stringify(form))

        var header = $("meta[name='_csrf_header']").attr('content');
        var token = $("meta[name='_csrf']").attr('content');
        $.ajax({
            type: "POST",
            url: "/user/update",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            cache: false,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(form),
            success: function (result) {
                console.log(result);
                $(location).attr('href', '/');
            },
            error: function (e) {
                console.log(e);
            }
        });
    });

    $("#delete_btn").click(function (event) {

        event.preventDefault();

        let username = $("#input_username").val();


        let form = {
            username: username
        };

        console.log(JSON.stringify(form))

        var header = $("meta[name='_csrf_header']").attr('content');
        var token = $("meta[name='_csrf']").attr('content');
        $.ajax({
            type: "POST",
            url: "/user/delete",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            cache: false,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(form),
            success: function (result) {
                console.log(result);
                $(location).attr('href', '/');
            },
            error: function (e) {
                console.log(e);
            }
        });
    });
});