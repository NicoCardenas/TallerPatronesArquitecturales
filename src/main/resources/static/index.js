var APIModule = (function () {
    let apiVersion = "v1";
    let usersUrl = "/api/" + apiVersion + "/users/"
    var getRequest = function (url, callback) {
        $.get(url, callback)
    }

    var postRequest = function (url, object, callback) {
        //debugger
        //$.post(url, object, callback);
        $.ajax({
            url: url,
            type: 'POST',
            contentType: 'application/json',
            success: function (data) {
                callback(data);
            },
            error: function (data) {
                console.log("error: " + JSON.stringify(data));
            },
            data: JSON.stringify(object)
        });

    }
    return {
        // Get from the api
        getUsers: function (callback) { getRequest(usersUrl, callback) },
        // Post to the api
        createUser: function (object, callback) { postRequest(usersUrl, object, callback) }
    }
})();

var registerModule = (function () {
    var register = function () {
        var name = document.getElementById('name').value;
        var lastname = document.getElementById('lastname').value;

        var user = { name: name, lastname: lastname};
        APIModule.createUser(user, function (data) { 
            getModule.load();
        });
        name = "";
        lastname = "";
    }
    return {
        register: register
    }
})();

var getModule = (function () {
    
    var loadUsers = function () {
        APIModule.getUsers(function (data) { 
            let tbody = document.getElementById("data");
            tbody.innerText = "";
            let id = 1;
            data.forEach(element => {
                var tr = document.createElement("tr");
                var th = document.createElement("th");
                var tdName = document.createElement("td");
                var tdLastName = document.createElement("td");

                var node = document.createTextNode(id);
                var node1 = document.createTextNode(element.name);
                var node2 = document.createTextNode(element.lastname);

                th.scope = "row";

                th.appendChild(node);
                tdName.appendChild(node1);
                tdLastName.appendChild(node2);

                tr.appendChild(th);
                tr.appendChild(tdName);
                tr.appendChild(tdLastName);

                id += 1;
                tbody.appendChild(tr);
            });
        })
    }

    return {
        load: loadUsers
    }
})();

var firstLoad = function () {
    //document.getElementById("submite").addEventListener("click", registerModule.register());
    getModule.load();
}

document.body.addEventListener("load", firstLoad());