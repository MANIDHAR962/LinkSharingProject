
$(document).on('focusout', '#inviteEmail', function () {
    var email = this.value;
    var check = $.ajax({
        url: "/checkEmailExistsInSystem",
        data: {"email": email},
        method: "GET"
    });
    check.done(function (data) {
        if (data=="yourself") {
            document.getElementById('inviteEmail').value = null;
            $('#email-msg').text("Cannot send invitation to yourself");
            console.log(data);
        }
        else if(data=="pass"){
            $('#email-msg').text("registered email");
            console.log(data);
        }
        else {
            console.log(data);
            document.getElementById('inviteEmail').value = null;
            $('#email-msg').text("Not a registed email Id..");
        }
    });
    check.fail(function (jqXHR, textStatus) {
        document.getElementById('reg-email').value = "not valid";
        console.log("Error in fetching emails");
    })
})