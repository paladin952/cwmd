angular.module('cwmd').service('UserSrv', function (Restangular) {
    var service = this;
    var _currentUser = {
        username : "Mock.username",
        password: "Mock.password"
    };

    service.getUsers = function () {
        debugger;
        return Restangular.all('user').getList();
    };

    service.login = function (username, password) {
        var _params = {
            username: username ? username : '',
            password: password ? password : ''
        };

        Restangular.all('login').post(_params)
            .then(function (response) {
                _currentUser = response;
                service.isAdmin();
            })
            .catch(function (response) {
                //do nothing;
            })
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