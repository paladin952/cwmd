angular.module('cwmd').service('UserSrv', function (Restangular) {
    var service = this,
        _currentUser = {};

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
                _currentUser = response;
                return _currentUser;
            });
    };

    service.logout = function () {
        _currentUser = {};
        //TODO: call backend method to close the session or stuff
    };

    service.getUserRole = function () {
        return _currentUser.role;
    };

    service.getCurrentUser = function () {
        return _currentUser;
    };

    service.isAdmin = function () {
        return _currentUser.role == "ROLE_ADMIN";
    };

    service.isManager = function () {
        return _currentUser.role == "ROLE_MANAGER";
    };

    service.isContributor = function () {
        return _currentUser.role == "ROLE_CONTRIBUTOR";
    };

    service.isReader = function () {
        return _currentUser.role == "ROLE_READER";
    };

});