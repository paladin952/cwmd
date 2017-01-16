angular.module('cwmd').service('LogSrv', function (Restangular) {
    var service = this;

    service.getFlows = function () {
         return Restangular.all('flow').getList();
    };

    service.getFlowById = function (flowId) {
        var path = 'flow/' + flowId;

        return Restangular.one(path).get(flowId);
    };

    service.goToNextDepartment = function (flowId) {
        var path = 'flow/goToNextDepartment/';

        return Restangular.one(path).get(flowId);
    };

    service.isFlowAtEnd = function (flowId) {
        var path = 'flow/isAtEnd';

        return Restangular.one(path).get(flowId);
            // .then(function (response) {
            //     isAtEnd = response;
            // },
            // function (error, status) {
            //     isAtEnd = "Error with status " + status;
            // });
    };

    service.getFlowsStartedByCurrentUser = function(username){
        // var path = 'flow/forUser/' + username;

        return Restangular.all(path).getList();
    };

    service.getFlowsAssignedToCurrentUser = function(username){
        // var path = 'flow/forUser/' + username;

        return Restangular.all(path).getList();
    };

    service.getActiveFlows = function () {
        var path = 'flow/all/active';

        return Restangular.all(path).getList();
    };

    service.getFinishedFlows = function () {
        var path = 'flow/all/finished';

        return Restangular.all(path).getList();
    };

    service.updateFlow = function (flow) {
        return Restangular.one('flow').put(flow);
    };

    service.deleteFlow = function (flowId) {
        return Restangular.one('flow').delete(flowId);
    };

    service.countAllFlows = function () {
        return Restangular.one('flow/count').get();
    };

    service.countAllActiveFlows = function () {
        return Restangular.one('flow/count/active').get();
    };

    service.countAllFinishedFlows = function () {
        return Restangular.one('flow/count/finished').get();
    };

});