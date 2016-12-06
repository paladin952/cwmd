angular.module('cwmd').service('DRDocumentSrv', function (Restangular) {
    var service = this;

    service.getDocuments = function () {
        return Restangular.all('document').getList();
    };
});