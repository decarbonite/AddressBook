$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "/ajax-too-good",
        datatype : "application/json",
        contentType: "application/json"
    }).then(function(data) {
        console.log(data)
        for (let book of data){
            for(let buddy of book.buddies){
                $('table tbody').append("<tr><td>" + book.id + "</td><td>" + buddy.id + "</td><td>" + buddy.name + "</td><td>" + buddy.phoneNumber + "</td></tr>");
            }
        }
    });
});