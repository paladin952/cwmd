angular.module('cwmd').service('DepartmentSrv', function (Restangular) {
    var service = this;

    service.getDepartments = function () {
        return Restangular.all('department').getList();
    };

});