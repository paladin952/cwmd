angular.module('cwmd').component('myTasks', {
    templateUrl: 'app/js/user/my-tasks/myTasks.html',
    controller: function ($window, FlowSrv) {
        var $ctrl = this;
        $ctrl.remark = null;
        $ctrl.flows = [];

        $ctrl.$onInit = function () {
            FlowSrv.getFlowsAssignedToCurrentUser()
                .then(function (response) {
                    $ctrl.flows = response;
                })
                .catch(function (response) {
                    console.log(response);
                });
        };

        $ctrl.sendToNextDepartment = function (id) {
            FlowSrv.goToNextDepartment(id)
                .then(function (response) {
                    alert("Flow " + response + " was sent to the next department");
                    _.remove($ctrl.flows, function (flow) {
                        return flow.id === id;
                    });
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

        $ctrl.reject = function (flowId) {
            FlowSrv.rejectFlow(flowId)
                .then(function () {
                    _.remove($ctrl.flows, function (flow) {
                        return flow.id === flowId;
                    });
                });
        };

    }
});