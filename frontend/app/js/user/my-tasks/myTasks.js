angular.module('cwmd').component('myTasks', {
    templateUrl: 'app/js/user/my-tasks/myTasks.html',
    controller: function (FlowSrv) {
        var $ctrl = this;
        $ctrl.flows = null;

        $ctrl.$onInit = function () {
            FlowSrv.getFlowsAssignedToCurrentUser()
                .then(function (response) {
                    $ctrl.flows = response;
                })
                .catch(function (response) {
                    console.log(response);
                });
        };

        $ctrl.sendToNextDepartment = function(id){
            FlowSrv.goToNextDepartment(id)
                .then(function (response) {
                    alert("Flow " + response + " was sent to the next department");
                })
                .catch(function (response) {
                    console.log(response);
                });
        };

        $ctrl.sentToInitial = function(id){

        };
    }
});