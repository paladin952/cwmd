angular.module('cwmd').service('FlowSrv', function (Restangular, UserSrv) {
    var service = this;

    service.startFlow = function(documentIds, departmentIds){
        var _params = {
            documentIds : documentIds,
            departmentIds :  departmentIds
        };
        return Restangular.all('flow/start').post(_params);
    };

    service.getFlows = function () {
         return Restangular.all('flow').getList();
    };

    service.getFlowById = function (flowId) {
        var path = 'flow/' + flowId;

        return Restangular.one(path).get(flowId);
    };

    service.goToNextDepartment = function (flowId) {
        var path = 'flow/goToNextDepartment/' + flowId;

        return Restangular.one(path).get();
    };

    service.goToInitialDepartment = function (flowId, remark) {
        var parameters = {
            flowId: flowId,
            remark: remark
        };

        return Restangular.all('flow').post(parameters);
    };

    service.isFlowAtEnd = function (flowId) {
        var path = 'flow/isAtEnd';

        return Restangular.one(path).get(flowId);
    };

    service.getFlowsStartedByCurrentUser = function(){
        return Restangular.all('flow').getList();
    };

    service.getFlowsAssignedToCurrentUser = function(){
        var username = UserSrv.getCurrentUser();
        var path = 'flow/forUser/' + username;

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

    service.rejectFlow = function (flowId) {
        var path = 'flow/reject/' + flowId;
        return Restangular.one(path).post();
    };

});