angular.module('cwmd').component('manageUsers', {
    templateUrl: '/app/js/user/manageUsers/manageUsers.html',
    controller: function (DepartmentSrv, UserSrv, $state) {
        var $ctrl = this;
        $ctrl.roles= ["ADMIN", "MANAGER", "CONTRIBUTOR", "READER"];

        $ctrl.newUser = {
            username : "",
            password: "",
            firstName: "",
            lastName: "",
            address: "",
            email: "",
            phoneNumber: "",
            departmentChief: false,
            department: "",
            role: ""
        };

        $ctrl.$onInit = function () {
            DepartmentSrv.getDepartments()
                .then(function (response) {
                    $ctrl.departments = response;
                })
        };

        $ctrl.submit = function () {
            UserSrv.createUser($ctrl.newUser)
                .then(function (response) {
                    console.log(response);
                    $state.go("workingArea");
                })
                .catch(function (response) {
                    console.log(response);
                })
        }
    }
});