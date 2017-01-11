angular.module('cwmd').service('UserSrv', function (Restangular, $window, $state) {
    var service = this;

    service.getUsers = function () {
         return Restangular.all('user').getList();
    };

    service.login = function (username, password) {
        var _params = {
            username: username,
            password: password
        };

        return Restangular.all('login').post('login',_params)
            .then(function (response) {
                if (response != null) {
                    $window.localStorage.setItem("currentUsername", response.username);
                    $window.localStorage.setItem("currentUserRole", response.role);
                }
                return response;
            });
    };

    service.createUser = function (user) {
        var _params = {
            username : user.username ? user.username : "",
            password: user.password ? user.password : "",
            firstName: user.firstName ? user.firstName : "",
            lastName: user.lastName ? user.lastName : "",
            address: user.address ? user.address : "",
            email: user.email ? user.email : "",
            phoneNumber: user.phoneNumber ? user.phoneNumber : 0,
            departmentChief: user.departmentChief ? user.departmentChief : false,
            department: user.department.name ? user.department.name : "",
            role: user.role ? "ROLE_" + user.role : ""
        };

        return Restangular.all('user/create').post(_params);
    };

    service.logout = function () {
        $window.localStorage.removeItem("currentUsername");
        $window.localStorage.removeItem("currentUserRole");
        $state.go("login");
        //TODO: call backend method to close the session or stuff
    };

    service.getUserRole = function () {
        return $window.localStorage.getItem("currentUserRole");
    };

    service.getCurrentUser = function () {
        return $window.localStorage.getItem("currentUsername");
    };

    service.isAdmin = function () {
        var role = $window.localStorage.getItem("currentUserRole");
        return role == "ROLE_ADMIN";
    };

    service.isManager = function () {
        var role = $window.localStorage.getItem("currentUserRole");
        return role == "ROLE_MANAGER";
    };

    service.isContributor = function () {
        var role = $window.localStorage.getItem("currentUserRole");
        return role == "ROLE_CONTRIBUTOR";
    };

    service.isReader = function () {
        var role = $window.localStorage.getItem("currentUserRole");
        return role == "ROLE_READER";
    };

});