angular.module('cwmd').component('openTasks', {
    templateUrl: 'app/js/user/open-tasks/openTasks.html',
    controller: function (FlowSrv) {
        var $ctrl = this;

        $ctrl.$onInit = function () {
            FlowSrv.getFlows()
                .then(function (response) {
                    $ctrl.flow = response;
                })
                .catch(function (response) {
                    console.log(response);
                });    
        };
    }
});