angular.module('cwmd').service('UserSrv', function (Restangular) {
    var service = this;
    var _currentUser = {
        username : "user",
        password: "pass"
    };

    service.getUsers = function () {
         return Restangular.all('user').getList();
    };

    service.login = function (username, password) {
        if (!username || !password){
            return;
        }

        var _params = {
            username: username,
            password: password
        };

        Restangular.all('login').post(_params)
            .then(function (response) {
                _currentUser = response;
            });
    };

    service.logout = function () {
        _currentUser = {};
        //TODO: call backend method to close the session or stuff
    };

    service.getUserRole = function () {
        return _currentUser.User.role;
    };

    service.getCurrentUser = function () {
        return _currentUser;
    };

    service.isAdmin = function () {
        return _currentUser.User.role == "ROLE_ADMIN";
    };

    service.isManager = function () {
        return _currentUser.User.role == "ROLE_MANAGER";
    };

    service.isContributor = function () {
        return _currentUser.User.role == "ROLE_CONTRIBUTOR";
    };

    service.isReader = function () {
        return _currentUser.User.role == "ROLE_READER";
    };

});