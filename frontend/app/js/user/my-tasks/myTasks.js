angular.module('cwmd').component('myTasks', {
    templateUrl: 'app/js/user/my-tasks/myTasks.html',
    controller: function ($window, FlowSrv) {
        var $ctrl = this;
        $ctrl.flows = null;
        $ctrl.remark = null;

        $ctrl.$onInit = function () {
            var username = $window.localStorage.getItem("currentUsername");
            FlowSrv.getFlowsAssignedToCurrentUser(username)
                .then(function (response) {
                    $ctrl.flows = response;
                    debugger;
                })
                .catch(function (response) {
                    console.log(response);
                });
        };

        $ctrl.sendToNextDepartment = function (id) {
            FlowSrv.goToNextDepartment(id)
                .then(function (response) {
                    alert("Flow " + response + " was sent to the next department");
                })
                .catch(function (response) {
                    console.log(response);
                });
        };

        $ctrl.sentToInitial = function (id) {
            FlowSrv.goToInitialDepartment(id, $ctrl.remark)
                .then(function (response) {
                    alert("Flow " + response + " was sent to the initial department");
                })
                .catch(function (response) {
                    console.log(response);
                });
        };
    }
});