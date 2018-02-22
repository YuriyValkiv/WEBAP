function change(id) {
    var hiddenrow = $("#hiddenRow"+id);
    if (hiddenrow.is(":visible")) {
        hiddenrow.slideUp();
    } else {
        hiddenrow.slideDown();
    }
}

function add() {
    var hiddenrowTwo = $("#hiddenRowTwo");
    if (hiddenrowTwo.is(":visible")) {
        hiddenrowTwo.slideUp();
    } else {
        hiddenrowTwo.slideDown();
        $("#btn-add").hide();
    }
}

function removeUser(id) {
    $.ajax({
        url: "/user/" + id,
        type: "GET",

        success: function () {
            $('#row'+id).remove();
            $('#hiddenRow'+id).remove();
        }
    })
}

var userData = {};
function editUser(id) {
    userData.name = $('#name' + id).val();
    userData.password = $('#password' + id).val();
    userData.firstName = $('#firstName' + id).val();
    userData.email = $('#email' + id).val();

    $.ajax({
        url: "/user/" + id,
        type: "POST",
        data: userData,

        success: function () {
            $('.container').load('/user');
        }
    })
}