angular.module('cwmd').component('openTasks', {
    templateUrl: 'app/js/user/open-tasks/openTasks.html',
    controller: function (FlowSrv, DepartmentSrv) {
        var $ctrl = this;
        $ctrl.flows = null;
        $ctrl.departments = null;

        $ctrl.$onInit = function () {
            // var currentUser = $window.localStorage.currentUser;
            FlowSrv.getActiveFlows()
                .then(function (response) {
                    $ctrl.flows = response;
                })
                .catch(function (response) {
                    console.log(response);
                });

            DepartmentSrv.getDepartments()
                .then(function (response) {
                    $ctrl.departments = response;
                })
        };
    }
});